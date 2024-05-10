package com.wilczek;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestcontainersTest extends AbstractTestContainers{

    //testy w celu sprawdzenia czy kontener został utworzony oraz czy pracuje.
    @Test
    void canStartPostgresDB() {
        assertThat(postgreSQLContainer.isRunning()).isTrue();
        assertThat(postgreSQLContainer.isCreated()).isTrue();
        System.out.println();
    }
}