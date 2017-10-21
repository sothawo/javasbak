/*
 * (c) Copyright 2017 sothawo
 */
package com.sothawo.javasbak;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class ChatDisplay extends Panel {
    private Label text;

    public ChatDisplay() {
        setSizeFull();
        text = new Label();
        text.setContentMode(ContentMode.HTML);
        final VerticalLayout layout = new VerticalLayout();
        layout.addComponent(text);
        setContent(layout);
    }

    public void addMessage(final String user, final String message) {
        final String value = text.getValue();
        if (null == value || value.isEmpty()) {
            text.setValue("<em>" + user + "</em> " + message);
        } else {
            text.setValue(value + "<br/><em>" + user + "</em> " + message);
        }
        setScrollTop(Integer.MAX_VALUE);
    }
}
