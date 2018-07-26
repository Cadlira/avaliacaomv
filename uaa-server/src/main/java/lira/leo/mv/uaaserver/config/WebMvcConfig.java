package lira.leo.mv.uaaserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Classe responsável pelo mapeamento das views
 * @author leonardo.lira
 *
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	 @Override
     public void addViewControllers(ViewControllerRegistry registry) {
         registry.addViewController("login").setViewName("login");
         registry.addViewController("/oauth/confirm_access").setViewName("authorize");
         registry.addViewController("/oauth/register").setViewName("register");
         registry.addViewController("/").setViewName("login");
     }
}
