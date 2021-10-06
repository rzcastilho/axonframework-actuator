package com.github.rzcastilho.axonframework.actuate;

import org.axonframework.spring.config.AxonConfiguration;
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

    private final AxonConfiguration configuration;

    public AxonServerHealthIndicator(AxonConfiguration configuration) {
        super("AxonServer health check failed");
        Assert.notNull(configuration, "AxonConfiguration must not be null");
        this.configuration = configuration;
    }

    @Override
    protected void doHealthCheck(Builder builder) throws Exception {
        if (configuration.isRunning()) {
            builder.up();
        } else {
            builder.down();
        }
    }
}
