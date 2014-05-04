package com.shisholik.pages.filters;

import com.sun.istack.NotNull;

public final class AllFiltersAcceptedList implements FilterList {
    private static AllFiltersAcceptedList instance;

    @Override
    public boolean contains(String propertyName) {
        return true;
    }

    @Override
    public FilterList getFilterList(@NotNull String propertyName) {
        return null;
    }

    public static AllFiltersAcceptedList getInstance() {
        if (instance == null) {
            instance = new AllFiltersAcceptedList();
        }
        return instance;
    }

    private AllFiltersAcceptedList() {
    }
}
