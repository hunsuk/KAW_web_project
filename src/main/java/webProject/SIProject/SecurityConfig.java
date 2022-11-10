package webProject.SIProject;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/user/**").authenticated()
                // user주소에 대해서 인증을 요구한다
                .antMatchers("/manager/**").access("hasRole('ADMIN') or hasRole('MANAGER')")
                // manager주소는 ROLE_MANAGER권한이나 ROLE_ADMIN권한이 있어야 접근할 수 있다.
                .antMatchers("/admin/**").hasRole("ADMIN")
                // admin주소는 ROLE_ADMIN권한이 있어야 접근할 수 있다.
                .anyRequest().permitAll()	// 나머지주소는 인증없이 접근 가능하다
        .and()
                .formLogin()
                    .loginPage("/loginForm")
                    .usernameParameter("id")
                    .passwordParameter("pw")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/")
                    .failureUrl("/loginForm")
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/");
        http.cors().and();

        http.csrf().disable();
    }
}
