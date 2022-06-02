package com.sistema.olimpiadas;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  @Bean
  protected UserDetailsService userDetailsService() {
    UserDetails usuario1 = User
        .withUsername("Juez1")
        .password("$2a$10$21k9QLX0vdSytZ36T56yHeDwn2y6DiHdJ2k7fCzccV7CuskmNQmEq")
        .roles("USER")
        .build();

    UserDetails usuario2 = User
        .withUsername("admin")
        .password("$2a$10$21k9QLX0vdSytZ36T56yHeDwn2y6DiHdJ2k7fCzccV7CuskmNQmEq")
        .roles("ADMIN")
        .build();

    UserDetails usuario3 = User
        .withUsername("Entrenador1")
        .password("$2a$10$21k9QLX0vdSytZ36T56yHeDwn2y6DiHdJ2k7fCzccV7CuskmNQmEq")
        .roles("ENTRENADOR")
        .build();

    UserDetails usuario4 = User
        .withUsername("Competidor1")
        .password("$2a$10$21k9QLX0vdSytZ36T56yHeDwn2y6DiHdJ2k7fCzccV7CuskmNQmEq")
        .roles("COMPETIDOR")
        .build();

    return new InMemoryUserDetailsManager(usuario1, usuario2, usuario3, usuario4);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/").authenticated()
        .antMatchers("/form/*", "/eliminar/*", "/crear_disciplina/*", "/eliminaDis/*").hasRole("ADMIN")
        .antMatchers("/crear_competidor/*", "/eliminarComp/*").hasRole("ENTRENADOR")
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .permitAll()
        .and()
        .logout().permitAll();
  }
}
