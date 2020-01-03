package com.example.demo;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class Gauges {

  private SimpleMeterRegistry registry = new SimpleMeterRegistry();

  public void gauge() {
    AtomicInteger value = registry.gauge("gauge1", new AtomicInteger(0));
    value.set(1);

    List<String> list = registry.gaugeCollectionSize("list.size", Collections.emptyList(), new ArrayList<>());
    list.add("a");

    Map<String, String> map = registry.gaugeMapSize("map.size", Collections.emptyList(), new HashMap<>());
    map.put("a", "b");

    Gauge.builder("value", this, Gauges::getValue)
        .description("a simple gauge")
        .tag("tag1", "a")
        .register(registry);
  }

  private double getValue() {
    return ThreadLocalRandom.current().nextDouble();
  }
}
