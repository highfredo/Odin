package es.us.isa.odin.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import es.us.isa.odin.events.CreatedDateMongoEventListener;
import es.us.isa.odin.events.EditedDateMongoEventListener;
import es.us.isa.odin.events.PrePostPersistMongoEventListener;
import es.us.isa.odin.interceptors.HttpErrorInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "es.us.isa.odin" })
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/favicon.ico").addResourceLocations("/favicon.ico");
	}
	
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

	@Override 
	public void addInterceptors(InterceptorRegistry registry) {
		HttpErrorInterceptor httpErrorInterceptor = new HttpErrorInterceptor();
		registry.addInterceptor(httpErrorInterceptor); 
	}
	
	/*
	 *  BEANS 
	 */
	
	@Bean
	public PrePostPersistMongoEventListener prePostPersistMongoEventListener() {
		return new PrePostPersistMongoEventListener();
	}
	
	@Bean
	public EditedDateMongoEventListener editedDateMongoEventListener() {
		return new EditedDateMongoEventListener();
	}
	
	@Bean
	public CreatedDateMongoEventListener createdDateMongoEventListener() {
		return new CreatedDateMongoEventListener();
	}
}