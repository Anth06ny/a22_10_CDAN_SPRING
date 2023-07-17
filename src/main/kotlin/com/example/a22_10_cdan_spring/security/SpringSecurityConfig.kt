package com.example.a22_10_cdan_spring.security

import org.springframework.beans.factory.annotation.Autowired


/*
@Configuration
open class SpringSecurityConfig {

    @Autowired
    open fun configureGlobal(auth : AuthenticationManagerBuilder) {
        val encoder = BCryptPasswordEncoder()

        //Créer des utilisateurs fixes
        auth.inMemoryAuthentication()
            .passwordEncoder(encoder)
            .withUser("aaa")
                .password(encoder.encode("aaa"))
                .roles("USER")
            .and()
                .withUser("Admin")
                .password(encoder.encode("Admin"))
                .roles("ADMIN")
    }

    @Bean
    open fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http.authorizeHttpRequests()
            .requestMatchers("/testPrivate").authenticated()
            .requestMatchers("/testPrivateAdmin").hasRole("ADMIN")
            .anyRequest().permitAll()
            .and().formLogin()
            .and().build()
    }
}          */