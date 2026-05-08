package com.gguek.app.config;

import com.gguek.app.member.service.MemberService;
import com.gguek.app.member.service.MemberServiceImpl;
import com.gguek.app.security.LoginFailHandler;
import com.gguek.app.security.LoginSuccessHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private MemberServiceImpl memberServiceImpl;

    // 정적 자원에 대한 설정
    @Bean
    WebSecurityCustomizer customizer() {
        return web -> {
            web.ignoring()
                    .requestMatchers("/css/**", "/images/**", "/img/**", "/js/**", "/vendor/**", "/scss/**")
                    .requestMatchers("/files/**", "/fileDown/**");
        };
    }

    // 인증과 인가에 대한 설정
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        security
                .cors(cors -> {
                    cors.disable();
                })
                .csrf(csrf -> {
                    csrf.disable();
                })

                .authorizeHttpRequests(auth -> {
                    auth
                            .requestMatchers("/notice/create", "/notice/update", "/notice/delete").hasRole("ADMIN")
                            .requestMatchers("/qna/detail", "/qna/create", "/qna/update", "/qna/delete")
                            .hasRole("MEMBER")
                            .requestMatchers("/product/create", "/product/update", "/product/delete")
                            .hasAnyRole("ADMIN", "MANAGER")
                            .requestMatchers("/member/mypage", "/member/logout", "/member/update").authenticated()
                            .requestMatchers("/member/login").permitAll()
                            .anyRequest().permitAll()
                    // .anyRequest().denyAll()
                    ;
                })
                // Login Form과 관련된 설정
                .formLogin(form -> {
                    form
                            .loginPage("/member/login")
                            // .usernameParameter("username")
                            // .passwordParameter("password")
                            .loginProcessingUrl("/member/login").permitAll()
                            // .defaultSuccessUrl("/")
                            .successHandler(loginSuccessHandler)
                            .failureUrl("/")
                            .failureHandler(new LoginFailHandler());
                })

                .logout(logout -> {
                    logout
                            .logoutUrl("/member/logout")
                            .invalidateHttpSession(true)
                            .deleteCookies("JSESSIONID")
                            // .logoutSuccessUrl("/")
                            .addLogoutHandler(new AddLogout())
                            .logoutSuccessHandler(new AddLogoutHandler());
                })

                .rememberMe(remember -> {
                    remember
                            .rememberMeParameter("rememberMe")
                            .key("rememberKey")
                            .tokenValiditySeconds(60 * 30)
                            .userDetailsService(memberServiceImpl)
                            .authenticationSuccessHandler(loginSuccessHandler)
                            .useSecureCookie(true);
                })
                .sessionManagement(session -> {
                    session
                            .maximumSessions(1)
                            .maxSessionsPreventsLogin(false)
                            .expiredUrl("/member/login");
                });
        return security.build();
    }

}
