package com.example.demo;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.FunctionCounter;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import java.util.ArrayList;
import java.util.List;

public class Counters {

  private SimpleMeterRegistry registry = new SimpleMeterRegistry();

  private double value = 0.0;

  public void counter() {
    Counter counter1 = registry.counter("simple1");
    counter1.increment(2.0);
    Counter counter2 = Counter.builder("simple2")
        .description("A simple counter")
        .tag("tag1", "a")
        .register(registry);
    counter2.increment();
  }

  public void functionCounter() {
    List<Tag> tags = new ArrayList<>();
    registry.more().counter("function1", tags, this, Counters::getValue);

    FunctionCounter functionCounter = FunctionCounter.builder("function2", this, Counters::getValue)
        .description("A function counter")
        .tags(tags)
        .register(registry);
    functionCounter.count();
  }

  private double getValue() {
    return value++;
  }
}
