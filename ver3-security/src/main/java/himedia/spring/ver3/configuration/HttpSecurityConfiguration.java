package himedia.spring.ver3.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class HttpSecurityConfiguration {
	
	@Bean
	protected SecurityFilterChain secutiryFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf((csrf) -> csrf.disable())
			.authorizeHttpRequests((authorizeRequest) ->
				authorizeRequest.requestMatchers("/", "/signIn", "/signOut", "/success").permitAll()
				.anyRequest().authenticated())
//				.exceptionHandling(error -> error.accessDeniedPage(""))
			.formLogin((formLogin) -> formLogin.loginPage("/signIn")
					.defaultSuccessUrl("/sign/success", true)
					.usernameParameter("username")
					.passwordParameter("password")
					.loginProcessingUrl("/signIn"));
		http.logout(logout -> logout.logoutUrl("/signOut")
				.logoutSuccessUrl("/signOut")
				.invalidateHttpSession(true));
		
		return http.build();
	}
	
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("user1").password("{noop}1111").roles("USER");
	}
	
//	@Bean
//	protected PasswordEncoder passwordEncoder() {
//		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//	}
}
