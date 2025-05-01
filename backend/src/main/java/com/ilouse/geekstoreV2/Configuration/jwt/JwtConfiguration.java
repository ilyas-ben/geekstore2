package com.ilouse.geekstoreV2.Configuration.jwt;

import com.ilouse.geekstoreV2.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class JwtConfiguration {

    @Autowired
    private AuthEntryPointJwt unauthorizedHANDLER;

    @Bean
    public AuthTokenFilter tokenFilter(){
        return new AuthTokenFilter();
    }

    @Autowired
    private UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry->{
                    registry.requestMatchers("/admin").hasRole("admin");
                    registry.requestMatchers("/signin").permitAll();
                    registry.requestMatchers("/error").permitAll();
                    registry.requestMatchers("/products/**").permitAll();
                    registry.requestMatchers("/categories").permitAll();
                    registry.anyRequest().authenticated();
                })
                .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(headers->headers
                                .frameOptions(frameOptions->frameOptions.sameOrigin())
                        )
                .exceptionHandling(handling -> handling.authenticationEntryPoint(unauthorizedHANDLER))
                .addFilterBefore(tokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    @Bean
    public UserDetailsService userDetailsService(){
        return this.userService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
}


    /* @Bean
    public UserDetailsService userDetails(){
        UserDetails user = User.builder()
                .username("ilyas")
                .password("{noop}lola")
                .roles("pr")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
*/


