package com.smallworldfs.socks;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Solution {

    public int solution(int washingCapacity, int[] cleanSocks, int[] dirtySocks) {
        return new SocksLaundromat(washingCapacity, cleanSocks, dirtySocks).wash().countCleanPairs();
    }

    static class SocksLaundromat {

        private static final int MAX_COLORS = 50;

        private final Map<Integer, SockHolder> socksByColor;
        private int washingCapacity;

        public SocksLaundromat(int washingCapacity, int[] cleanSocks, int[] dirtySocks) {
            this.socksByColor = new HashMap<>(MAX_COLORS);
            this.washingCapacity = washingCapacity;
            addSocks(cleanSocks, SockHolder::addClean);
            addSocks(dirtySocks, SockHolder::addDirty);
        }

        private void addSocks(int[] socks, Consumer<SockHolder> classifier) {
            Arrays.stream(socks).mapToObj(this::getOrCreateSockHolder).forEach(classifier);
        }

        private SockHolder getOrCreateSockHolder(int color) {
            return socksByColor.computeIfAbsent(color, k -> new SockHolder());
        }

        public SocksLaundromat wash() {
            holdersWithDirtySocks().filter(SockHolder::hasOddClean).forEach(this::washOne);

            if (washingCapacity > 1) {
                holdersWithDirtySocks().forEach(this::washMaxPairs);
            }
            return this;
        }

        private void washOne(SockHolder socks) {
            if (washingCapacity > 0) {
                deductWashingCapacity(socks.washOne());
            }
        }

        private void washMaxPairs(SockHolder socks) {
            deductWashingCapacity(socks.washMaxPairs(washingCapacity));
        }

        private void deductWashingCapacity(int washedSocks) {
            washingCapacity -= washedSocks;
        }

        private Stream<SockHolder> holdersWithDirtySocks() {
            return socksByColor.values().stream().filter(SockHolder::hasDirty);
        }

        public int countCleanPairs() {
            return socksByColor.values().stream().mapToInt(SockHolder::getCleanPairs).sum();
        }
    }

    static class SockHolder {

        private int dirty;
        private int clean;

        public void addDirty() {
            dirty++;
        }

        public void addClean() {
            clean++;
        }

        public boolean hasOddClean() {
            return isOdd(clean);
        }

        public boolean hasDirty() {
            return dirty > 0;
        }

        public int washOne() {
            return wash(1);
        }

        public int washMaxPairs(int max) {
            return wash(getClosestEven(Math.min(max, dirty)));
        }

        public int getCleanPairs() {
            return clean / 2;
        }

        private int getClosestEven(int number) {
            return isOdd(number) ? number - 1 : number;
        }

        private int wash(int amount) {
            dirty -= amount;
            clean += amount;

            return amount;
        }

        private boolean isOdd(int number) {
            return number % 2 == 1;
        }
    }
}
