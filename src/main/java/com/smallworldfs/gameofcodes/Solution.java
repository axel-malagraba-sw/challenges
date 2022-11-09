package com.smallworldfs.gameofcodes;

import java.util.LinkedList;
import java.util.List;

public class Solution {

    private IndexedString string;

    public int solution(String string) {
        this.string = new IndexedString(string);

        return computeMaxLength();
    }

    private int computeMaxLength() {
        int maxLength = string.getMaxCombinedFrequency();

        for (int cut : string.getCutPoints()) {
            int left = string.getMaxLeftFrequency(cut);
            int maxRightBlock = string.getMaxRightBlockSize(cut);
            int charsLeft = string.length() - cut;
            int bigM = (int) Math.ceil(maxRightBlock * charsLeft / (maxRightBlock + 1F)) + maxRightBlock;
            int maxAchievable = left + bigM;

            if (maxAchievable < maxLength) {
                continue;
            }
            int length = left + string.reindexLeftFrom(cut).getMaxCombinedFrequency();
            maxLength = Math.max(length, maxLength);
        }
        return maxLength;
    }

    static class IndexedString {

        protected final char[] string;
        protected final int from;
        protected final List<Integer> cutPoints;
        protected final int[] maxRightFrequency;
        protected final int[] maxRightBlocks;
        protected final int[] maxLeftFrequency;
        protected final int[] charFrequencies;

        protected int maxCombinedFrequency;

        IndexedString(String string) {
            this(string.toCharArray(), 0, new LinkedList<>(), new int[string.length()], new int[string.length()]);
            indexRightToLeft();
            indexLeftToRight();
        }

        private IndexedString(IndexedString string, int from) {
            this(string.string, from, string.cutPoints, string.maxRightFrequency, string.maxRightBlocks);
            indexLeftToRight();
        }

        private IndexedString(
                char[] string, int from, List<Integer> cutPoints, int[] maxRightFrequency, int[] maxRightBlocks) {
            this.from = from;
            this.string = string;
            this.cutPoints = cutPoints;
            this.maxRightFrequency = maxRightFrequency;
            this.maxRightBlocks = maxRightBlocks;
            this.maxLeftFrequency = new int[string.length + 1];
            this.charFrequencies = new int['z' - 'a' + 1];
        }

        public IndexedString reindexLeftFrom(int from) {
            return new IndexedString(this, from);
        }

        private void indexLeftToRight() {
            boolean shouldEvaluateCutPoints = this.cutPoints.isEmpty();
            int maxFrequency = 0;
            int[] charFrequencies = new int['z' - 'a' + 1];

            for (int i = from; i < length(); i++) {
                maxFrequency = Math.max(maxFrequency, ++charFrequencies[string[i] - 'a']);
                maxLeftFrequency[i + 1] = maxFrequency;
                updateMaxCombinedFrequency(i);

                if (shouldEvaluateCutPoints) {
                    evaluateCutPointCandidate(i);
                }
            }
        }

        private void evaluateCutPointCandidate(int index) {
            if (isValidCutPointCandidate(index)) {
                cutPoints.add(index);
            }
        }

        private boolean isValidCutPointCandidate(int index) {
            return index > 0
                    && index < length() - 1
                    && string[index - 1] != string[index]
                    && maxLeftFrequency[index] > maxLeftFrequency[index - 1];
        }

        private void indexRightToLeft() {
            int maxFrequency = 0;
            char lastCharacter = 0;
            char lastBlockSize = 0;

            for (int i = length() - 1; i >= from; i--) {
                char character = string[i];
                maxFrequency = Math.max(maxFrequency, ++charFrequencies[character - 'a']);
                maxRightFrequency[i] = maxFrequency;
                updateMaxCombinedFrequency(i);

                if (character == lastCharacter) {
                    lastBlockSize++;
                } else {
                    lastBlockSize = 1;
                }
                updateMaxRightBlockSize(i, lastBlockSize);
                lastCharacter = character;
            }
        }

        private void updateMaxRightBlockSize(int index, int blockSize) {
            if (index == length() - 1) {
                maxRightBlocks[index] = 1;
                return;
            }
            maxRightBlocks[index] = Math.max(blockSize, maxRightBlocks[index + 1]);
        }

        private void updateMaxCombinedFrequency(int index) {
            maxCombinedFrequency = Math.max(maxCombinedFrequency, getCombinedFrequency(index));
        }

        public int getMaxRightFrequency(int index) {
            return maxRightFrequency[index];
        }

        public int getMaxRightBlockSize(int index) {
            return maxRightBlocks[index];
        }

        public int getMaxLeftFrequency(int index) {
            return maxLeftFrequency[index];
        }

        public int getCombinedFrequency(int index) {
            return getMaxRightFrequency(index) + getMaxLeftFrequency(index);
        }

        public List<Integer> getCutPoints() {
            return cutPoints;
        }

        public int getMaxCombinedFrequency() {
            return maxCombinedFrequency;
        }

        public int length() {
            return string.length;
        }
    }
}
