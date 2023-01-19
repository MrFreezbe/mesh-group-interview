package ru.meshgroup.interview;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class,})
@Import({MeshApplication.class})
public class SpringTestConfig {
}
