package org.kee.vue.server.vhr.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.kee.vue.server.vhr.model.Hr;
import org.kee.vue.server.vhr.model.Response;
import org.kee.vue.server.vhr.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.PrintWriter;

/**
 * @Author： 羽子少年
 * @Date： 2020/6/10 23:53
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    HrService hrService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(hrService);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/doLogin")
                .loginPage("/login")
                .successHandler((req, resp, auth) -> {
                    resp.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                    PrintWriter out  = resp.getWriter();
                    Hr hr = (Hr) auth.getPrincipal();
                    hr.setPassword(null);
                    Response ok = Response.ok("登陆成功", hr);
                    String s = new ObjectMapper().writeValueAsString(ok);
                    out.write(s);
                    out.flush();
                    out.close();
                })
                .failureHandler((req, resp, exception) -> {
                    resp.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                    PrintWriter out  = resp.getWriter();
                    Response error = Response.error("登陆失败");

                    if (exception instanceof LockedException) {
                        error.setMsg("账号被锁定，请联系管理员");
                    } else if (exception instanceof CredentialsExpiredException) {
                        error.setMsg("密码过期，请联系管理员");
                    } else if (exception instanceof AccountExpiredException) {
                        error.setMsg("账号过期，请联系管理员");
                    } else if (exception instanceof DisabledException) {
                        error.setMsg("账号被禁用，请联系管理员");
                    } else if (exception instanceof BadCredentialsException) {
                        error.setMsg("账号或密码输入错误，请联系管理员");
                    }

                    out.write(new ObjectMapper().writeValueAsString(error));
                    out.flush();
                    out.close();
                })
                .permitAll()
                .and()
                .logout()
                .logoutSuccessHandler((req, resp, auth) -> {
                    resp.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                    PrintWriter out  = resp.getWriter();
                    Hr hr = (Hr) auth.getPrincipal();
                    out.write(new ObjectMapper().writeValueAsString(Response.ok("注销成功", hr)));
                    out.flush();
                    out.close();
                })
                .permitAll()
                .and()
                .csrf().disable();
    }
}
