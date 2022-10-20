package com.example.demo.securitypage;

import com.example.demo.common.JwtUtil;
import com.example.demo.common.RedisCache;
import com.example.demo.pojo.LoginUser;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Objects;


/**
 * 自定义过滤器，放在了最前面
 * 每一个请求都经过这里
 */

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {


    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        if (Objects.isNull(token)) {
            filterChain.doFilter(request, response);
            //放行之后一定要加return不然会回来的
            return;
        }
        Claims claims;
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException("token 出现错误");
        }
        String subject = claims.getSubject();

        String redisKey = "login:" + subject;
        //这个就是authon对象
        Object loginUser = redisCache.getCacheObject(redisKey);
        LoginUser loginUser1 = (LoginUser) loginUser;

        if (Objects.isNull(loginUser)) {
            throw new RuntimeException("你还没有登录");
        }
        //获取权限信息 存进去
//        Collection<? extends GrantedAuthority> authorities = loginUser.getAuthorities();
        Authentication authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, loginUser1.getAuthorities());

        //后面权限验证不然会抛异常
        //SecurityContext context = SecurityContextHolder.getContext();
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }
}
