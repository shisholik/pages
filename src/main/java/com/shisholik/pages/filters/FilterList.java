package com.shisholik.pages.filters;

import com.sun.istack.NotNull;

/**
 * Created by shisholik on 04.05.14.
 */
public interface FilterList {


    boolean contains(String propertyName);

    FilterList getFilterList(@NotNull String propertyName);
}
