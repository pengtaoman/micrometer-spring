package com.example.demo;

import io.micrometer.core.instrument.LongTaskTimer;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

public class Timers {
  private SimpleMeterRegistry registry = new SimpleMeterRegistry();

  public void record() {
    Timer timer = registry.timer("simple");
    timer.record(() -> {
      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
  }

  public void sample() {
    Timer.Sample sample = Timer.start();
    new Thread(() -> {
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      sample.stop(registry.timer("sample"));
    }).start();
  }

  public void longTask() {
    LongTaskTimer timer = registry.more().longTaskTimer("long");
    timer.record(() -> {
      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
  }
}
