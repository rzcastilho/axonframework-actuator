package com.github.rzcastilho.axonframework.actuate;

import io.axoniq.axonserver.connector.AxonServerConnection;
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

    private final AxonServerConnection connection;

    public AxonServerHealthIndicator(AxonServerConnection connection) {
        super("AxonServer health check failed");
        Assert.notNull(connection, "AxonServerConnection must not be null");
        this.connection = connection;
    }

    @Override
    protected void doHealthCheck(Builder builder) throws Exception {
        if (connection.isConnected() && connection.isReady()) {
            builder.up();
        } else {
            builder.down();
        }
    }
}
