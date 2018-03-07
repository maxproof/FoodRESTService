package config;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;

import service.FoodRestService;

/**
 * Configures the runtime, and registers the application with jax-rs.
 * 
 * @author taylor
 *
 */
@ApplicationPath("rs")
public class ApplicationConfig extends Application {
	private final Set<Class<?>> classes;

	/**
	 * Constructor - Registers the classes used in the rest application.
	 * 
	 */
	public ApplicationConfig() {
		HashSet<Class<?>> c = new HashSet<>();
		c.add(FoodRestService.class);
		c.add(MOXyJsonProvider.class);
		classes = Collections.unmodifiableSet(c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.core.Application#getClasses()
	 */
	@Override
	public Set<Class<?>> getClasses() {
		return classes;
	}

}
