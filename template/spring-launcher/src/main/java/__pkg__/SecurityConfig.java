package {{ group_id }}.{{ app_package }};

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfig {
  @Bean
  public Customizer<HttpSecurity> appSecurityCustomizer() {
    return http ->
        http.authorizeHttpRequests(
            auth -> auth.requestMatchers("/people", "/people/**").permitAll());
  }
}
