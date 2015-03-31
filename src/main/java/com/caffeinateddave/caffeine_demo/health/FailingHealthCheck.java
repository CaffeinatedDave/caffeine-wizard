package com.caffeinateddave.caffeine_demo.health;

import com.codahale.metrics.health.HealthCheck;

public class FailingHealthCheck extends HealthCheck {
  
  public FailingHealthCheck() {}
  
  @Override
  protected Result check() throws Exception {
	  return Result.unhealthy("I'm always unhealthy *sadface*");
  }
}
