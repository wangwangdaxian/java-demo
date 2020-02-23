package com.shizk.demo.java.tools.cfg4j;


import org.cfg4j.provider.ConfigurationProvider;
import org.cfg4j.provider.ConfigurationProviderBuilder;
import org.cfg4j.source.ConfigurationSource;
import org.cfg4j.source.classpath.ClasspathConfigurationSource;
import org.cfg4j.source.context.environment.Environment;
import org.cfg4j.source.context.environment.ImmutableEnvironment;
import org.cfg4j.source.context.filesprovider.ConfigFilesProvider;
import org.cfg4j.source.files.FilesConfigurationSource;
import org.cfg4j.source.reload.ReloadStrategy;
import org.cfg4j.source.reload.strategy.PeriodicalReloadStrategy;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class Cfg4jDemo {

    public static void classpath() {
        ConfigFilesProvider configFilesProvider = () -> Arrays.asList(Paths.get("application.yaml"), Paths.get("otherConfig.properties"));
        ConfigurationSource source = new ClasspathConfigurationSource(configFilesProvider);
        Environment environment = new ImmutableEnvironment("tools/cfg4j");
        ConfigurationProvider provider = new ConfigurationProviderBuilder()
                .withConfigurationSource(source)
                .withEnvironment(environment)
                .build();
        String set1 = provider.getProperty("some.setting", String.class);
        System.out.println(set1);
    }

    public static void file() {
        ConfigFilesProvider configFilesProvider = () -> Collections.singletonList(Paths.get("application.yaml"));
        ConfigurationSource source = new FilesConfigurationSource(configFilesProvider);
        Environment environment = new ImmutableEnvironment("/Users/shizk233/IdeaProjects/java-demo/src/main/resources/tools/cfg4j");
        ReloadStrategy reloadStrategy = new PeriodicalReloadStrategy(5, TimeUnit.SECONDS);
        ConfigurationProvider provider = new ConfigurationProviderBuilder()
                .withConfigurationSource(source)
                .withEnvironment(environment)
                .withReloadStrategy(reloadStrategy)
                .build();
        String set1 = provider.getProperty("some.setting", String.class);
        System.out.println(set1);
    }


    public static void main(String[] args) {
        classpath();
        file();
    }

}
