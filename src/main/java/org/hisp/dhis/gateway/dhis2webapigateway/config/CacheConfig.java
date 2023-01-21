package org.hisp.dhis.gateway.dhis2webapigateway.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {

    public static final String DATA_ELEMENT_GROUPS_CACHE = "dataElementGroupsCache";
    public static final String DATA_ELEMENTS_CACHE = "dataElementsCache";

    @Value("${cache.refresh.interval.minutes}")
    private int cacheRefreshIntervalMinutes;

    @Bean
    public Caffeine caffeine() {
        return Caffeine.newBuilder().expireAfterWrite(cacheRefreshIntervalMinutes, TimeUnit.MINUTES);
    }

    @Bean
    public CaffeineCacheManager caffeineCacheManager(Caffeine caffeine) {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager(DATA_ELEMENTS_CACHE, DATA_ELEMENT_GROUPS_CACHE);
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }
}
