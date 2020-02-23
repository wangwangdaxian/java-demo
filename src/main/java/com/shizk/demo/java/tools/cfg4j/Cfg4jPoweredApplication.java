package com.shizk.demo.java.tools.cfg4j;

import org.cfg4j.provider.ConfigurationProvider;
import org.cfg4j.provider.ConfigurationProviderBuilder;
import org.cfg4j.source.ConfigurationSource;
import org.cfg4j.source.git.GitConfigurationSourceBuilder;
import org.cfg4j.source.reload.strategy.PeriodicalReloadStrategy;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Cfg4jPoweredApplication {
    // "Reksio" is a friendly dog that has many friends
    public interface ReksioConfig {
        List<String> friends();
    }

    public static void main(String... args) {

        // Select our sample git repository as the configuration source
        ConfigurationSource source = new GitConfigurationSourceBuilder()
                .withRepositoryURI("https://github.com/cfg4j/cfg4j-git-sample-config.git")
                .build();

        // Create configuration provider backed by the source
        ConfigurationProvider provider = new ConfigurationProviderBuilder()
                .withConfigurationSource(source)
                .withReloadStrategy(new PeriodicalReloadStrategy(5, TimeUnit.SECONDS))
                .build();

        // Get info about our dog. When you add more friends in the configuration file, object below will automatically reflect that change (by mutating friends() list).
        ReksioConfig reksioConfig = provider.bind("reksio", ReksioConfig.class);

        // Display friends of Reksio!
        System.out.println(reksioConfig.friends());
    }
}
