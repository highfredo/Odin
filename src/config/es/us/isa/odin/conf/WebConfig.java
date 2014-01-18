package es.us.isa.odin.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

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

	/*
	 * @Override public void addInterceptors(InterceptorRegistry registry) {
	 * 
	 * LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
	 * localeChangeInterceptor.setParamName("lang"); registry.addInterceptor(localeChangeInterceptor); }
	 * 
	 * @Bean public LocaleResolver localeResolver() {
	 * 
	 * CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
	 * cookieLocaleResolver.setDefaultLocale(StringUtils.parseLocaleString("en")); return cookieLocaleResolver; }
	 * 
	 * @Bean public ViewResolver viewResolver() {
	 * 
	 * InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	 * viewResolver.setViewClass(JstlView.class); viewResolver.setPrefix("/WEB-INF/views");
	 * viewResolver.setSuffix(".jsp"); return viewResolver; }
	 * 
	 * @Bean public MessageSource messageSource() {
	 * 
	 * ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	 * messageSource.setBasenames("classpath:messages/messages", "classpath:messages/validation"); // if true, the key
	 * of the message will be displayed if the key is not // found, instead of throwing a NoSuchMessageException
	 * messageSource.setUseCodeAsDefaultMessage(true); messageSource.setDefaultEncoding("UTF-8"); // # -1 : never
	 * reload, 0 always reload messageSource.setCacheSeconds(0); return messageSource; }
	 */
}