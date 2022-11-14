package com.smallworldfs.socks;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Solution {

    public int solution(int washingCapacity, int[] cleanSocks, int[] dirtySocks) {
        Laundromat laundromat = new Laundromat(washingCapacity);
        laundromat.addSocks(cleanSocks, SockHolder::clean);
        laundromat.addSocks(dirtySocks, SockHolder::dirty);
        laundromat.washDirtySocks();

        return laundromat.countCleanPairs();
    }

    static class Laundromat {

        private static final int MAX_COLORS = 50;

        private final Map<Integer, SockHolder> socksByColor;
        private int washingCapacity;

        public Laundromat(int washingCapacity) {
            this.socksByColor = new HashMap<>(MAX_COLORS);
            this.washingCapacity = washingCapacity;
        }

        public void addSocks(int[] socks, Consumer<SockHolder> classifier) {
            Arrays.stream(socks).mapToObj(this::getOrCreateSockHolder).forEach(classifier);
        }

        private SockHolder getOrCreateSockHolder(int color) {
            return socksByColor.computeIfAbsent(color, k -> new SockHolder());
        }

        public void washDirtySocks() {
            List<SockHolder> socks = sortSocksByPriority();
            socks.forEach(this::wash);

            if (washingCapacity > 1) {
                socks.forEach(this::wash);
            }
        }

        private List<SockHolder> sortSocksByPriority() {
            return socksByColor.values()
                    .stream()
                    .sorted(Comparator.comparing(this::calculatePriority))
                    .collect(Collectors.toList());
        }

        private int calculatePriority(SockHolder socks) {
            if (socks.hasOddClean()) {
                return socks.hasOddDirty() ? 0 : 1;
            }
            return 2;
        }

        private void wash(SockHolder socks) {
            if (socks.hasOddClean() && washingCapacity > 0) {
                washingCapacity -= socks.washOne();
                return;
            }
            washingCapacity -= socks.washMaxPairs(washingCapacity);
        }

        public int countCleanPairs() {
            return socksByColor.values().stream().mapToInt(SockHolder::getCleanPairs).sum();
        }
    }

    static class SockHolder {

        private int dirty;
        private int clean;

        public void dirty() {
            dirty++;
        }

        public void clean() {
            clean++;
        }

        public boolean hasOddClean() {
            return isOdd(clean);
        }

        public boolean hasOddDirty() {
            return isOdd(dirty);
        }

        public int washOne() {
            return wash(1);
        }

        public int washMaxPairs(int max) {
            return wash(getClosestEven(Math.min(max, dirty)));
        }

        public int getCleanPairs() {
            return (int) Math.floor(clean / 2F);
        }

        private int getClosestEven(int number) {
            return isOdd(number) ? number - 1 : number;
        }

        private int wash(int amount) {
            if (dirty >= amount) {
                dirty -= amount;
                clean += amount;

                return amount;
            }
            return 0;
        }

        private boolean isOdd(int number) {
            return number % 2 == 1;
        }
    }
}
