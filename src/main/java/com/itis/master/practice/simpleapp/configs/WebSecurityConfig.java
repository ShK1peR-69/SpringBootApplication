package com.itis.master.practice.simpleapp.configs;

import com.itis.master.practice.simpleapp.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/*****
 * @author Igor Astafyev
 * January 2022
 * Web security settings
 *****/

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserServiceImpl userService;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				.csrf()
				.disable()
				.authorizeRequests()
				//Доступ только для не зарегистрированных пользователей
				.antMatchers("/registration", "/").not().fullyAuthenticated()
				//Доступ только для пользователей с ролью Администратор
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/profile").fullyAuthenticated()
				//Доступ разрешен всем пользователей
				.antMatchers("/", "/resources/**").permitAll()
				//Все остальные страницы требуют аутентификации
				.anyRequest().authenticated()

				.and()
				.exceptionHandling().accessDeniedPage("/access-denied")

				.and()
				//Настройка для входа в систему
				.formLogin()
				.loginPage("/login")
				//Перенаправление на главную страницу после успешного входа
				.defaultSuccessUrl("/profile")
				.permitAll()

				.and()
				.logout()
				.permitAll()
				.logoutSuccessUrl("/")

				.and()
				.rememberMe()
				.key("uniqueAndSecret");
	}

	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
	}
}