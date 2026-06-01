package dev.openfeature.demo.java.demo;

import dev.openfeature.contrib.providers.flagd.FlagdOptions;
import dev.openfeature.contrib.providers.flagd.FlagdProvider;
import dev.openfeature.contrib.providers.flagd.Config;
import dev.openfeature.sdk.OpenFeatureAPI;
import dev.openfeature.sdk.exceptions.OpenFeatureError;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenFeatureConfig {

    @Bean
    public OpenFeatureAPI openFeatureAPI() {
        FlagdOptions options = FlagdOptions.builder()
                .resolverType(Config.Resolver.RPC)
                .build();

        OpenFeatureAPI api = OpenFeatureAPI.getInstance();
        try {
            api.setProviderAndWait(new FlagdProvider(options));
        } catch (OpenFeatureError e) {
            throw new IllegalStateException("Failed to initialise flagd RPC provider", e);
        }
        return api;
    }
}