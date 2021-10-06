package com.github.rzcastilho.axonframework.actuate;

import org.axonframework.axonserver.connector.AxonServerConnectionManager;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health.Builder;
import org.springframework.util.Assert;

/**
 * Implementation of a {@link org.springframework.boot.actuate.health.HealthIndicator}
 * returning connection status with Axon Server
 *
 * @author rzcastilho
 */
public class AxonServerHealthIndicator extends AbstractHealthIndicator {

    private final AxonServerConnectionManager connection;

    public AxonServerHealthIndicator(AxonServerConnectionManager configuration) {
        super("AxonServer health check failed");
        Assert.notNull(configuration, "AxonConfiguration must not be null");
        this.connection = configuration;
    }

    @Override
    protected void doHealthCheck(Builder builder) throws Exception {
        if (connection.isConnected(connection.getDefaultContext())) {
            builder.up();
        } else {
            builder.down();
        }
    }
}
