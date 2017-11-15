package com.gtn.configuration;

import java.util.Set;
import javax.servlet.MultipartConfigElement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {

	private static final Logger logger = LoggerFactory.getLogger(WebApplicationInitializer.class);

	public void onStartup(ServletContext servletContext) throws ServletException {
		servletContext.setInitParameter("defaultHtmlEscape", "true");

		// now the config for the Dispatcher servlet
		AnnotationConfigWebApplicationContext mvcContext = new AnnotationConfigWebApplicationContext();
		mvcContext.register(MvcConfig.class);
		
		mvcContext.setServletContext(servletContext);
//		 The main Spring MVC servlet.
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(
				mvcContext));
		if(dispatcher != null){
			
			dispatcher.addMapping("/");
			Set<String> mappingConflicts = dispatcher.addMapping("/gtn/*");
			dispatcher.setLoadOnStartup(1);
			MultipartConfigElement multipartConfigElement = new MultipartConfigElement("d:\\files", 5242880L, 26214400L, 1048576);
		      

		      dispatcher.setMultipartConfig(multipartConfigElement);	
			if (!mappingConflicts.isEmpty()) {
				for (String s : mappingConflicts) {
					logger.error("Mapping conflict: " + s);
				}
				throw new IllegalStateException("'dispatcher' cannot be mapped to '/' under Tomcat versions <= 7.0.14");
			}
		}
		
		servletContext.addFilter("ServletFilter", new CORSFilter());
		logger.info("****GTN initialised ***");
	}

}
