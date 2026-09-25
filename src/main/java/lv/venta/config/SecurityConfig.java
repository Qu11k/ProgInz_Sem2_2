package lv.venta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public UserDetailsManager createDummyUsers() {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		UserDetails userD1= User.builder()
				.username("Martins")
						.password(encoder.encode("123"))
						.authorities("USER")
						.build();
		UserDetails userD2= User.builder()
			.username("Vins")
			.password(encoder.encode("321"))
			.authorities("USER")
			.build();
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager(userD1,userD2);
		return manager;
		
	}
}
