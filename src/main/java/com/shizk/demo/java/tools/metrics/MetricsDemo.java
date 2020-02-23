package com.shizk.demo.java.tools.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

import java.util.concurrent.TimeUnit;

public class MetricsDemo {
    public static final MetricRegistry metrics = new MetricRegistry();

    public static void startReport() {
        ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(1, TimeUnit.SECONDS);
    }

    public static void waitSeconds() {
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException ignored) {

        }
    }

    public static void main(String[] args) {
        meter();
    }

    public static void meter() {
        startReport();
        Meter requests = metrics.meter("requests");
        requests.mark();
        waitSeconds();
    }

    public static void histogram() {
        Histogram histogram = metrics.histogram("histogram");
        histogram.update(1);
    }
}
