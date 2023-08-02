package com.s02springbootcourse.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties (prefix = "currency-service")
@Getter
@Setter
@Component
public class CurrencyServiceConfiguration {

    private String url;
    private String username;
    private String password;
}
