package com.shisholik.pages.filters;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class DefaultFilterManager implements FilterManager {
    private FilterList currentList;

    private LinkedList<FilterList> queue = new LinkedList<>();

    public DefaultFilterManager(FilterList currentList) {
        this.currentList = currentList;
    }

    @Override
    public void push(String propertyName) {
        queue.addLast(currentList);
        currentList = currentList.getFilterList(propertyName);
        if (currentList == null) {
            currentList = AllFiltersAcceptedList.getInstance();
        }


    }


    @Override
    public boolean contains(String propertyName) {
        return currentList.contains(propertyName);
    }

    @Override
    public void pop() {
        currentList = queue.removeLast();
    }
}
