package com.colak.springcloudstreamrabbitmqtutorial.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
@Slf4j
public class FunctionConfig {

    // A Function is a processor. That is why it created 2 exchanges!
    // It listens to toUpper-in-0 and sends the output to toUpper-out-0
    @Bean
    public Function<String, String> toUpper() {
        return str -> {
            log.info(STR."Original: \{str}");
            return str.toUpperCase();
        };
    }

    @Bean
    public Consumer<String> consumeUpper() {
        return upper -> {
            log.info(STR."Consumed: \{upper}");
        };
    }


    // Supplier is a little different that a Function or a Consumer.
    // Functions/Consumers are triggered whenever there is an input.
    // But a Supplier doesn’t have a trigger.
    // In Spring Cloud Function, the Supplier has an automated polling mechanism which polls the supplier every second
    // (which you can override)
    // So this method is called periodically
    @Bean
    public Supplier<String> createData() {
        return () -> {
            System.out.println("Creating some data");
            return STR."test-data-\{ThreadLocalRandom.current().nextInt(0, 10000)}-\{LocalDateTime.now()}";
        };
    }
}
