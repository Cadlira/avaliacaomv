package lira.leo.mv.userrs.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Classe de configuração do servidor de recurso
 * @author leonardo.lira
 *
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	@Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/swagger**").permitAll()
                .antMatchers("/swagger**").permitAll()
                .antMatchers("/swagger-ui.html/swagger-resources/**").permitAll()
                .antMatchers("/v2/api-docs**").permitAll()
                .antMatchers("/mappings/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers(HttpMethod.GET, "/**").access("#oauth2.hasScope('read')")
                .antMatchers(HttpMethod.PUT, "/**").access("#oauth2.hasScope('write')")
                .antMatchers(HttpMethod.DELETE, "/**").access("#oauth2.hasScope('write')")
                .antMatchers(HttpMethod.POST, "/**").access("#oauth2.hasScope('write')");
        // Apenas para testes com H2, dessa forma � poss�vel visualizar ap�s o login
     	http.csrf().disable();
     	http.headers().frameOptions().disable();
    }
}
