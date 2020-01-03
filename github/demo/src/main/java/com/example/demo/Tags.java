package com.example.demo;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

public class Tags {

  public static void main(String[] args) {
    SimpleMeterRegistry registry = new SimpleMeterRegistry();
    registry.config().commonTags("tag1", "a", "tag2", "b");
    Counter counter = registry.counter("simple", "tag3", "c");
    counter.increment();
  }
}
