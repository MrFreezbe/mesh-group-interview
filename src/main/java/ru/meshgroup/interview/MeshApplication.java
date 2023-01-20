package ru.meshgroup.interview;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.retry.annotation.EnableRetry;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

@Slf4j
@EnableRetry
@SpringBootApplication
@EnableJpaRepositories(basePackages = "ru.meshgroup.interview.repository")
public class MeshApplication {

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(MeshApplication.class);
        Environment env = app.run(args).getEnvironment();
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        log.info(String.format("Application '%s' is running! " +
                        "Access URLs:%nLocal: %s://localhost:%s%nExternal: %s://%s:%s%nProfile(s): %s%n",
                env.getProperty("spring.application.name"),
                protocol,
                env.getProperty("server.port"),
                protocol,
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                Arrays.toString(env.getActiveProfiles())));
    }

}
