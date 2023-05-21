package com.smallworldfs.concurrency;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import lombok.SneakyThrows;

public class BoundBlockingQueue<E> {

    private final int capacity;
    private final AtomicInteger size = new AtomicInteger();
    private final Queue<E> elements = new LinkedList<>();
    private final ReentrantLock putLock = new ReentrantLock();
    private final Condition notFull = putLock.newCondition();
    private final ReentrantLock takeLock = new ReentrantLock();
    private final Condition notEmpty = takeLock.newCondition();

    public BoundBlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    @SneakyThrows
    public void put(E element) {
        checkNotNull(element);
        putLock.lockInterruptibly();

        try {
            awaitNotFull();
            addElement(element);

            int size = this.size.incrementAndGet();

            signalNotFullIfBelowCapacity(size);
            signalNotEmptyIfAboveZero(size);
        } finally {
            putLock.unlock();
        }
    }

    private void checkNotNull(E element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
    }

    private void awaitNotFull() throws InterruptedException {
        while (size.get() == capacity) {
            notFull.await();
        }
    }

    private void addElement(E element) {
        elements.add(element);
    }

    @SneakyThrows
    private void signalNotFullIfBelowCapacity(int size) {
        if (size < capacity) {
            putLock.lockInterruptibly();
            try {
                notFull.signal();
            } finally {
                putLock.unlock();
            }
        }
    }

    public int size() {
        return size.get();
    }

    @SneakyThrows
    public E take() {
        takeLock.lockInterruptibly();

        try {
            awaitNotEmpty();

            E element = takeElement();
            int size = this.size.decrementAndGet();

            signalNotEmptyIfAboveZero(size);
            signalNotFullIfBelowCapacity(size);

            return element;
        } finally {
            takeLock.unlock();
        }
    }

    private void awaitNotEmpty() throws InterruptedException {
        while (size.get() == 0) {
            notEmpty.await();
        }
    }

    private E takeElement() {
        return elements.poll();
    }

    @SneakyThrows
    private void signalNotEmptyIfAboveZero(int size) {
        if (size > 0) {
            takeLock.lockInterruptibly();
            try {
                notEmpty.signal();
            } finally {
                takeLock.unlock();
            }
        }
    }
}
