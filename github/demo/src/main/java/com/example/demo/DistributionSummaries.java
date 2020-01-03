package com.example.demo;

import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

public class DistributionSummaries {
  private SimpleMeterRegistry registry = new SimpleMeterRegistry();

  public void summary() {
    DistributionSummary summary = DistributionSummary.builder("simple")
        .description("simple distribution summary")
        .minimumExpectedValue(1L)
        .maximumExpectedValue(10L)
        .publishPercentiles(0.5, 0.75, 0.9)
        .register(registry);
    summary.record(1);
    summary.record(1.3);
    summary.record(2.4);
    summary.record(3.5);
    summary.record(4.1);
    System.out.println(summary.takeSnapshot());
  }

  public static void main(String[] args) {
    new DistributionSummaries().summary();
  }
}
