package com.shisholik.pages;

import com.shisholik.pages.filters.AllFiltersAcceptedList;
import com.shisholik.pages.filters.DefaultFilterList;
import com.shisholik.pages.filters.DefaultFilterManager;
import com.shisholik.pages.filters.FilterList;
import com.sun.istack.NotNull;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

import javax.servlet.ServletRequest;

public class FieldsInterceptor extends AbstractPhaseInterceptor<Message> {

    public FieldsInterceptor() {
        super(Phase.INVOKE);
    }

    @Override
    public void handleMessage(Message message) {
        String fields = ((ServletRequest) message.get("HTTP.REQUEST")).getParameter("fields");
        FilterList instance;
        if (fields != null) {
            instance = handleFieldsList(fields);
        } else {
            instance = AllFiltersAcceptedList.getInstance();
        }
        message.getExchange().put("fields", new DefaultFilterManager(instance, fields));
    }

    public FilterList handleFieldsList(@NotNull String fields) {
        if (fields.isEmpty()) {
            return AllFiltersAcceptedList.getInstance();
        }

        DefaultFilterList defaultFilterList = new DefaultFilterList();

        int nestedCount = 0;
        int startIndex = 0;
        int nestedStartedIndex = -1;
        String name;
        for (int i = 0; i < fields.toCharArray().length; i++) {
            switch (fields.toCharArray()[i]) {
                case ',':
                    if (nestedCount == 0) {

                        if (nestedStartedIndex != -1) {
                            name = fields.substring(startIndex, nestedStartedIndex);
                            FilterList filterList = handleFieldsList(fields.substring(nestedStartedIndex + 1, i - 1));
                            defaultFilterList.add(name, filterList);
                            nestedStartedIndex = -1;
                        } else {
                            name = fields.substring(startIndex, i);
                            defaultFilterList.add(name);
                        }
                        startIndex = i + 1;
                    }
                    break;
                case '(':
                    nestedStartedIndex = i;
                    nestedCount++;
                    break;
                case ')':
                    nestedCount--;
                    break;
            }
        }

        if (nestedStartedIndex != -1) {
            name = fields.substring(startIndex, nestedStartedIndex);
            FilterList filterList = handleFieldsList(fields.substring(nestedStartedIndex + 1, fields.length() - 1));
            defaultFilterList.add(name, filterList);
        } else {
            name = fields.substring(startIndex);
            defaultFilterList.add(name);
        }

        return defaultFilterList;
    }

    @Override
    public void handleFault(Message message) {
    }
}
