package com.shisholik.pages.filters;

import com.sun.istack.NotNull;

import java.util.*;

public class DefaultFilterList implements FilterList {
    private List<String> propertiesNames = new ArrayList<>();
    private Map<String, FilterList> filter2filterList = new HashMap<>();

    @Override
    public boolean contains(String propertyName) {
        return propertiesNames.contains(propertyName);
    }


    public void add(String propertyName) {
        propertiesNames.add(propertyName);
    }

    public void add(@NotNull String propertyName, @NotNull FilterList filterList) {
        add(propertyName);
        filter2filterList.put(propertyName, filterList);
    }

    public FilterList getFilterList(@NotNull String propertyName) {
        return filter2filterList.get(propertyName);
    }
}
