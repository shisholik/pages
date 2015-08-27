package com.shisholik.pages.filters;

import java.util.Deque;
import java.util.LinkedList;

public class DefaultFilterManager implements FilterManager {
    private FilterList currentList;

    private Deque<FilterList> queue = new LinkedList<>();

    private String fields;

    private boolean isInternal;

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public DefaultFilterManager(FilterList currentList, String fields) {
        this.currentList = currentList;
        this.fields = fields;
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
    public void setInternal(boolean isInternal) {
        this.isInternal = isInternal;
    }

    @Override
    public boolean isInternal() {
        return isInternal;
    }

    @Override
    public void pop() {
        currentList = queue.removeLast();
    }
}
