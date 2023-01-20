package org.hisp.dhis.gateway.dhis2webapigateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;

@Configuration
public class WebClientConfig {

    @Value("${dhis2.username}")
    private String username;

    @Value("${dhis2.password}")
    private String password;

    @Value("${dhis2.url}")
    private String dhis2Url;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(dhis2Url)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON.toString())
                .filter(basicAuthentication(username, password)).build();
    }
}
