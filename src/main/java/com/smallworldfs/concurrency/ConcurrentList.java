package com.smallworldfs.concurrency;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Consumer;
import java.util.function.Function;

public class ConcurrentList<E> implements List<E> {

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final List<E> delegate;

    public ConcurrentList(List<E> delegate) {
        this.delegate = delegate;
    }

    @Override
    public int size() {
        return read(List::size);
    }

    @Override
    public boolean isEmpty() {
        return read(List::isEmpty);
    }

    @Override
    public boolean contains(Object o) {
        return read(list -> list.contains(o));
    }

    @Override
    public Iterator<E> iterator() {
        return delegate.iterator();
    }

    @Override
    public Object[] toArray() {
        return read(List::toArray);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return read(list -> list.toArray(a));
    }

    @Override
    @SuppressWarnings("Contract")
    public boolean add(E e) {
        return writeAndReturn(list -> list.add(e));
    }

    @Override
    public boolean remove(Object o) {
        return writeAndReturn(list -> list.remove(o));
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return writeAndReturn(list -> list.addAll(c));
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return writeAndReturn(list -> list.addAll(index, c));
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return writeAndReturn(list -> list.removeAll(c));
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return writeAndReturn(list -> list.retainAll(c));
    }

    @Override
    public void clear() {
        write(List::clear);
    }

    @Override
    public E get(int index) {
        return read(list -> list.get(index));
    }

    @Override
    public E set(int index, E element) {
        return writeAndReturn(list -> list.set(index, element));
    }

    @Override
    public void add(int index, E element) {
        write(list -> list.add(index, element));
    }

    @Override
    public E remove(int index) {
        return writeAndReturn(list -> list.remove(index));
    }

    @Override
    public int indexOf(Object o) {
        return read(list -> list.indexOf(o));
    }

    @Override
    public int lastIndexOf(Object o) {
        return read(list -> list.lastIndexOf(o));
    }

    @Override
    public ListIterator<E> listIterator() {
        return delegate.listIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return delegate.listIterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return new ConcurrentList<>(read(list -> list.subList(fromIndex, toIndex)));
    }

    private <T> T read(Function<List<E>, T> function) {
        Lock readLock = readWriteLock.readLock();
        readLock.lock();

        try {
            return function.apply(delegate);
        } finally {
            readLock.unlock();
        }
    }

    private void write(Consumer<List<E>> consumer) {
        Lock writeLock = readWriteLock.writeLock();
        writeLock.lock();

        try {
            consumer.accept(delegate);
        } finally {
            writeLock.unlock();
        }
    }

    private <T> T writeAndReturn(Function<List<E>, T> function) {
        Lock writeLock = readWriteLock.writeLock();
        writeLock.lock();

        try {
            return function.apply(delegate);
        } finally {
            writeLock.unlock();
        }
    }
}
