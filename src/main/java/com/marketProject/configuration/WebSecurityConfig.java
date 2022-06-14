package com.marketProject.configuration;

import com.marketProject.configuration.Jwt.JwtAuthenticationFilter;
import com.marketProject.configuration.Jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()//api에서는 기본설정 x. 기본 설정은 비인증 시 로그인 화면으로 redirect 됨.
                .csrf().disable()// api에서 필요 없음.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // jwt token 사용할 거라 세션 필요 x
                .and()
                .authorizeRequests()
//                .antMatchers("/admin/**").hasRole("ADMIN") //요청에 대한 ROLE은 추후에 생각해서 추가 필요함.
//                .antMatchers("/members/**").hasRole("USER")
                .anyRequest().permitAll()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class); // jwt token 필터를 id/password 인증 필터 전에 넣음.
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //swagger 사용에서 막히지 않게 무시처리 한건데,,, 추후에 swagger공부하면서 다시 생각할 필요가 있을 듯.
        web.ignoring().antMatchers("/v2/api-docs", "/swagger-resource/**",
                "/swagger-ui.html", "/webjars/**", "/swagger/**");
    }
}
