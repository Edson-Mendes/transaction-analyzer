package br.com.emendes.transactionanalyzer.config.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@Profile("test")
public class TestWebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private DataSource dataSource;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/h2-console/**").permitAll()
        .anyRequest().authenticated()
        .and().csrf().disable()
        .headers().frameOptions().disable()
        .and()
        .formLogin(form -> form
            .loginPage("/signin")
            .permitAll()
            .defaultSuccessUrl("/transactions", true))
        .logout(logout -> logout.logoutUrl("/logout"));
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    BCryptPasswordEncoder passwordEnconder = new BCryptPasswordEncoder();

    auth.jdbcAuthentication()
        .dataSource(dataSource)
        .passwordEncoder(passwordEnconder);
  }

  // Configurações de recursos estáticos(js, css, img, etc)
  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring()
        .antMatchers("/swagger-ui/index.html", "/**.html", "/api-docs/**", "/webjars/**", "/configuration/**",
            "/swagger-resources/**", "/swagger-ui/**");
  }
}
