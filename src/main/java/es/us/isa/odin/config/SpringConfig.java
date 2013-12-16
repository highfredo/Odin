package es.us.isa.odin.config;


import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.springsecurity3.dialect.SpringSecurityDialect;
import org.thymeleaf.spring3.SpringTemplateEngine;
import org.thymeleaf.spring3.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import com.google.common.collect.Sets;
import com.mongodb.MongoClient;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"es.us.isa.odin"})
public class SpringConfig extends WebMvcConfigurerAdapter {

	// RESOURCES
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/favicon.ico").addResourceLocations("/favicon.ico");
	}
	//
	 
	// ServletHandling
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	//
	
	// DATA MONGO
	@Bean
	public MongoDbFactory mongoDbFactory() throws Exception {
		UserCredentials credentials = new UserCredentials("odin", "odin");
		return new SimpleMongoDbFactory(new MongoClient("ds057538.mongolab.com", 57538), "odindb", credentials);
	}
	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		return mongoTemplate;
	}
	//
	
	// THYMELEAF
	@Bean
	public ServletContextTemplateResolver thymeleafTemplateResolver() {
		ServletContextTemplateResolver context = new ServletContextTemplateResolver();
		context.setPrefix("/views/");
		context.setSuffix(".html");
		context.setTemplateMode("HTML5");
		context.setOrder(1);
		context.setCacheable(false);
		return context;
	}
	@Bean
	public SpringTemplateEngine thymeleafTemplateEngine() {
		Set<IDialect> dialects = new HashSet<>();
		dialects.add(new SpringSecurityDialect());
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(thymeleafTemplateResolver());
		templateEngine.setAdditionalDialects(dialects);
		return templateEngine;
	}
	@Bean
	public ThymeleafViewResolver thymeleafViewResolver() {
		ThymeleafViewResolver viewResolver =  new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(thymeleafTemplateEngine());
		return viewResolver;
	}
	//
	
}
