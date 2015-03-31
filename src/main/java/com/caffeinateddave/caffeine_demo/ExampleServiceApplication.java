package com.caffeinateddave.caffeine_demo;

import com.caffeinateddave.caffeine_demo.health.FailingHealthCheck;
import com.caffeinateddave.caffeine_demo.health.TemplateHealthCheck;
import com.caffeinateddave.caffeine_demo.resources.ExampleServiceResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
//import com.example.helloworld.health.TemplateHealthCheck;

/**
 * Hello world!
 *
 */
public class ExampleServiceApplication extends Application<ExampleServiceConfiguration> 
{
    public static void main( String[] args ) throws Exception {
        new ExampleServiceApplication().run(args);
    }
    
    @Override
    public String getName() {
    	return "hello-world";
    }
    
    @Override
    public void initialize(Bootstrap<ExampleServiceConfiguration> bootstrap) {
    	// Nothing here
    }
    
    @Override
    public void run(ExampleServiceConfiguration configuration, 
    				Environment environment) {
    	final ExampleServiceResource resource = new ExampleServiceResource(
    		configuration.getTemplate(),
    		configuration.getDefaultName()
    	);
    	final TemplateHealthCheck healthCheck =
    		new TemplateHealthCheck(configuration.getTemplate());
    	final FailingHealthCheck failedHealthCheck =
    		new FailingHealthCheck();
    	    	
    	environment.jersey().register(resource);
    	
    	environment.healthChecks().register("template", healthCheck);
    	environment.healthChecks().register("failing", failedHealthCheck);
    }
}
