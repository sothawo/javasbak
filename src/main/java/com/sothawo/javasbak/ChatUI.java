/*
 * (c) Copyright 2017 sothawo
 */
package com.sothawo.javasbak;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Push;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author P.J. Meisch (pj.meisch@sothawo.com)
 */
@SpringUI
@PreserveOnRefresh
@Push
public class ChatUI extends UI implements KafkaConnectorListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatUI.class);
    private final ChatDisplay chatDisplay = new ChatDisplay();
    private final KafkaConnector kafkaConnector;
    private String user;
    private Label userLabel = new Label();

    @Autowired
    public ChatUI(KafkaConnector kafkaConnector) {
        this.kafkaConnector = kafkaConnector;
    }


    @Override
    protected void init(VaadinRequest vaadinRequest) {
        kafkaConnector.addListener(this);
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.addComponents(chatDisplay, createInputs());
        layout.setExpandRatio(chatDisplay, 1f);
        setContent(layout);
        askForUserName();
    }

    @Override
    public void detach() {
        kafkaConnector.removeListener(this);
        super.detach();
        LOGGER.info("session ended for user " + user);
    }

    private Component createInputs() {
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setWidth(100f, Unit.PERCENTAGE);

        TextField messageField = new TextField();
        messageField.setWidth(100f, Unit.PERCENTAGE);
        Button button = new Button("Send");
        button.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        button.addClickListener(evt -> {
            kafkaConnector.send(user, messageField.getValue());
            messageField.clear();
            messageField.focus();
        });

        layout.addComponents(userLabel, messageField, button);
        layout.setComponentAlignment(userLabel, Alignment.MIDDLE_LEFT);
        layout.setExpandRatio(messageField, 1f);

        return layout;
    }

    private void askForUserName() {
        final Window window = new Window("your user:");
        window.setModal(true);
        window.setClosable(false);
        window.setResizable(false);

        VerticalLayout layout = new VerticalLayout();

        TextField nameField = new TextField();
        nameField.focus();

        layout.addComponent(nameField);
        Button button = new Button("OK");
        button.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        button.addClickListener(evt -> {
            user = nameField.getValue();
            if (null != user && !user.isEmpty()) {
                window.close();
                userLabel.setValue(user);
                LOGGER.info("user entered: " + user);
            }
        });

        layout.addComponent(button);

        window.setContent(layout);
        window.center();
        addWindow(window);
    }

    @Override
    public void chatMessage(String user, String message) {
        access(() -> chatDisplay.addMessage(user, message));
    }
}

