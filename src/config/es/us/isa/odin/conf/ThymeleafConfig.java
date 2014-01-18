package es.us.isa.odin.conf;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.springsecurity3.dialect.SpringSecurityDialect;
import org.thymeleaf.spring3.SpringTemplateEngine;
import org.thymeleaf.spring3.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Configuration
public class ThymeleafConfig {
	
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
	
}
