package gr.forth.ics.argql.app;

import org.glassfish.jersey.server.ResourceConfig;

public class ArgQLApplication extends ResourceConfig {

	public ArgQLApplication() {
		packages("gr.forth.ics.restservices");
	}
}
