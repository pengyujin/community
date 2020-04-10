package com.nowcoder.community.config;

import com.nowcoder.community.util.CommunityConstant;
import com.nowcoder.community.util.CommunityUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter implements CommunityConstant {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 授权
        http.authorizeRequests()
                .antMatchers(
                    "/user/setting",
                    "/user/upload",
                    "/discuss/add",
                    "/comment/add/**",
                    "/letter/**",
                    "/notice/**",
                    "/like",
                    "/follow",
                    "/unfollow"    
                ).hasAnyAuthority(
                        AUTHORINY_USER, 
                        AUTHORINY_ADMIN, 
                        AUTHORINY_MODERATOR
                ).antMatchers(
                        "/discuss/top",
                    "/discuss/wonderful")
                .hasAnyAuthority(
                        AUTHORINY_MODERATOR
                ).antMatchers(
                        "/discuss/delete",
                        "/data/**",
                        "/actuator/**"
                ).hasAnyAuthority(
                        AUTHORINY_ADMIN
                )
                .anyRequest().permitAll()
                .and().csrf().disable();
        
        // 权限
        http.exceptionHandling()
                .authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override // 没登陆，进入这里
                    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                        // 判断请求是同步还是异步，看消息头的值
                        String xRequestedWith = request.getHeader("x-requested-with");
                        if ("XMLHttpRequest".equals(xRequestedWith)) { //异步请求，返回字符串
                            response.setContentType("application/plain;charset=utf-8"); //确保是json格式，前端才能解析
                            PrintWriter writer = response.getWriter();
                            writer.write(CommunityUtil.getJSONString(403, "你还没有登陆"));
                        } else {
                            // 是同步请求，重定向
                            response.sendRedirect(request.getContextPath() + "/login");
                        }
                    }
                })
                .accessDeniedHandler(new AccessDeniedHandler() {
                    @Override // 登陆，权限不足，进入这里
                    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                        String xRequestedWith = request.getHeader("x-requested-with");
                        if ("XMLHttpRequest".equals(xRequestedWith)) { //异步请求，返回字符串
                            response.setContentType("application/plain;charset=utf-8"); //确保是json格式，前端才能解析
                            PrintWriter writer = response.getWriter();
                            writer.write(CommunityUtil.getJSONString(403, "你没有访问此功能的权限!"));
                        } else {
                            // 是同步请求，重定向
                            response.sendRedirect(request.getContextPath() + "/denied");
                        }
                    }
                });
        
        // spring security底层默认会拦截/logout请求进行退出的处理
        // 覆盖它默认的逻辑，执行自己的退出代码
        // 善意的欺骗，项目中没有/securitylogout路径
        http.logout().logoutUrl("/securitylogout");
    }
}
