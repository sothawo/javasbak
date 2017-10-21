/*
 * (c) Copyright 2017 sothawo
 */
package com.sothawo.javasbak;

/**
 * @author P.J. Meisch (pj.meisch@sothawo.com)
 */
public interface KafkaConnectorListener {
    void chatMessage(final String user, final String message);
}
