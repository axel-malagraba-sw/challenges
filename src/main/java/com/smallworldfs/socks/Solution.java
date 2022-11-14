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
            addSocks(cleanSocks, SockHolder::clean);
            addSocks(dirtySocks, SockHolder::dirty);
        }

        private void addSocks(int[] socks, Consumer<SockHolder> classifier) {
            Arrays.stream(socks).mapToObj(this::getOrCreateSockHolder).forEach(classifier);
        }

        private SockHolder getOrCreateSockHolder(int color) {
            return socksByColor.computeIfAbsent(color, k -> new SockHolder());
        }

        public SocksLaundromat wash() {
            dirtySocks().filter(SockHolder::hasOddClean).forEach(this::wash);

            if (washingCapacity > 1) {
                dirtySocks().forEach(this::wash);
            }
            return this;
        }

        private void wash(SockHolder socks) {
            if (socks.hasOddClean() && washingCapacity > 0) {
                washingCapacity -= socks.washOne();
                return;
            }
            washingCapacity -= socks.washMaxPairs(washingCapacity);
        }

        private Stream<SockHolder> dirtySocks() {
            return socksByColor.values().stream().filter(SockHolder::hasDirty);
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

        public boolean hasDirty() {
            return dirty > 0;
        }

        public int washOne() {
            return wash(Math.min(1, dirty));
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
            dirty -= amount;
            clean += amount;

            return amount;
        }

        private boolean isOdd(int number) {
            return number % 2 == 1;
        }
    }
}
