package com.example.demo;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

public class GlobalRegistryExample {

  public static void main(String[] args) {
    Metrics.addRegistry(new SimpleMeterRegistry());
    Counter counter = Metrics.counter("simple");
    counter.increment();
  }
}
