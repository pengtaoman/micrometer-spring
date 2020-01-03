package com.example.demo;

import io.micrometer.core.instrument.DistributionSummary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@RestController
@RequestMapping("/app")
public class AppController {
  private final Counter counter;
  private final DistributionSummary distributionSummary;

  public AppController(final MeterRegistry registry) {
    this.counter = registry.counter("greeting");

    this.distributionSummary = DistributionSummary.builder("distributionSummary")
            .description("simple distribution summary")
            .minimumExpectedValue(1L)
            .maximumExpectedValue(10L)
            .publishPercentiles(0.5, 0.75, 0.9)
            .register(registry);
  }

  @RequestMapping("/greeting")
  public String greeting() {
    this.counter.increment();
    return "hello world #" + this.counter.count();
  }

  @RequestMapping("/distributionSummary")
  public String distributionSummary() {
    this.distributionSummary.record(1);
    this.distributionSummary.record(1.3);
    this.distributionSummary.record(2.4);
    this.distributionSummary.record(3.5);
    this.distributionSummary.record(4.1);


    return "distributionSummarydistributionSummarydistributionSummarydistributionSummary";
  }
}
