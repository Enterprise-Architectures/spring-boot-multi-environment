package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(args = "--app.demo.precedence-message=from-command-line")
@ActiveProfiles("test")
class DemoApplicationTests {

    @Autowired
    private Environment environment;

    @Test
    void contextLoads() {
    }

    @Test
    void commandLineArgumentsOverrideApplicationYaml() {
        assertEquals(
                "from-command-line",
                environment.getProperty("app.demo.precedence-message"));
    }
}
