package com.example.demo;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import io.micrometer.core.instrument.simple.SimpleConfig;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

public class CompositeMeterRegistryExample {

  public static void main(String[] args) {
    CompositeMeterRegistry registry = new CompositeMeterRegistry();
    registry.add(new SimpleMeterRegistry());
    registry.add(new SimpleMeterRegistry(new MyConfig(), Clock.SYSTEM));

    Counter counter = registry.counter("simple");
    counter.increment();
  }

  private static class MyConfig implements SimpleConfig {

    public String get(final String key) {
      return null;
    }

    public String prefix() {
      return "my";
    }
  }
}
