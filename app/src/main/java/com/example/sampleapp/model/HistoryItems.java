package com.example.sampleapp.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * List with fixed size in which elements at indexes higher than the limit are discarded.
 * <p>
 * Used to represent history of searched strings in reverse chronological order.
 */
public class HistoryItems implements List<String> {

    private static final int MAX_HISTORY_ITEMS = 20;

    private ArrayList<String> searchStrings = new ArrayList<>(MAX_HISTORY_ITEMS);

    @Override
    public int size() {
        return searchStrings.size();
    }

    @Override
    public boolean isEmpty() {
        return searchStrings.isEmpty();
    }

    @Override
    public boolean contains(@Nullable Object o) {
        return searchStrings.contains(o);
    }

    @NonNull
    @Override
    public Iterator<String> iterator() {
        return searchStrings.iterator();
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return searchStrings.toArray();
    }

    @NonNull
    @Override
    public <T> T[] toArray(@NonNull T[] a) {
        return searchStrings.toArray(a);
    }

    @Override
    public boolean add(String s) {
        add(size(), s);
        return true;
    }

    public boolean addToStart(String s) {
        add(0, s);
        return true;
    }

    @Override
    public boolean remove(@Nullable Object o) {
        return searchStrings.remove(o);
    }

    @Override
    public boolean containsAll(@NonNull Collection<?> c) {
        return searchStrings.containsAll(c);
    }

    @Override
    public boolean addAll(@NonNull Collection<? extends String> c) {
        return addAll(size(), c);
    }

    @Override
    public boolean addAll(int index, @NonNull Collection<? extends String> c) {
        if (index >= MAX_HISTORY_ITEMS) {
            return false;
        }

        int currentIndex = index;
        for (String s : c) {
            if (currentIndex >= MAX_HISTORY_ITEMS) {
                break;
            }
            add(currentIndex, s);
            currentIndex++;
        }

        return true;
    }

    @Override
    public boolean removeAll(@NonNull Collection<?> c) {
        return searchStrings.removeAll(c);
    }

    @Override
    public boolean retainAll(@NonNull Collection<?> c) {
        return searchStrings.retainAll(c);
    }

    @Override
    public void clear() {
        searchStrings.clear();
    }

    @Override
    public String get(int index) {
        return searchStrings.get(index);
    }

    @Override
    public String set(int index, String element) {
        return searchStrings.set(index, element);
    }

    @Override
    public void add(int index, String element) {
        if (index >= MAX_HISTORY_ITEMS) {
            return;
        }

        searchStrings.add(index, element);
        if (size() > MAX_HISTORY_ITEMS) {
            remove(size() - 1);
        }
    }

    @Override
    public String remove(int index) {
        return searchStrings.remove(index);
    }

    @Override
    public int indexOf(@Nullable Object o) {
        return searchStrings.indexOf(o);
    }

    @Override
    public int lastIndexOf(@Nullable Object o) {
        return searchStrings.lastIndexOf(o);
    }

    @NonNull
    @Override
    public ListIterator<String> listIterator() {
        return searchStrings.listIterator();
    }

    @NonNull
    @Override
    public ListIterator<String> listIterator(int index) {
        return searchStrings.listIterator(index);
    }

    @NonNull
    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        return searchStrings.subList(fromIndex, toIndex);
    }
}
