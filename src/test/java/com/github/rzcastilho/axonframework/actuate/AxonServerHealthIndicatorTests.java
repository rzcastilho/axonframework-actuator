package com.github.rzcastilho.axonframework.actuate;

import org.axonframework.axonserver.connector.AxonServerConnectionManager;
import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class AxonServerHealthIndicatorTests {

    private static final String DEFAULT_CONTEXT = "default";

    @Test
    void axonServerIsUp() {
        AxonServerConnectionManager connection = mock(AxonServerConnectionManager.class);
        given(connection.getDefaultContext()).willReturn(DEFAULT_CONTEXT);
        given(connection.isConnected(DEFAULT_CONTEXT)).willReturn(true);
        AxonServerHealthIndicator healthIndicator = new AxonServerHealthIndicator(connection);
        Health health = healthIndicator.health();
        assertThat(health.getStatus()).isEqualTo(Status.UP);
    }

    @Test
    void axonServerIsDown() {
        AxonServerConnectionManager connection = mock(AxonServerConnectionManager.class);
        given(connection.getDefaultContext()).willReturn(DEFAULT_CONTEXT);
        given(connection.isConnected(DEFAULT_CONTEXT)).willReturn(false);
        AxonServerHealthIndicator healthIndicator = new AxonServerHealthIndicator(connection);
        Health health = healthIndicator.health();
        assertThat(health.getStatus()).isEqualTo(Status.DOWN);
    }

}
