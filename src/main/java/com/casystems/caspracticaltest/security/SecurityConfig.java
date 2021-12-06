package com.casystems.caspracticaltest.security;


import com.casystems.caspracticaltest.security.services.UserDetailsServiceImpl;
import com.casystems.caspracticaltest.system.models.Menu;
import com.casystems.caspracticaltest.system.models.Role;
import com.casystems.caspracticaltest.system.services.MenuService;
import com.casystems.caspracticaltest.system.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //remove security to this folders
    private final String[] resources = new String[]{
            "/css/**","/icons/**","/img/**"
    };

    @Autowired
    MenuService menuService;

    @Autowired
    RoleService roleService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        ArrayList<Menu> menus = menuService.getMenus();
        for(Menu menu:menus){
            ArrayList<Role> autorities = menuService.getAuthorities4MenuName(menu.getName());
            for(Role auth:autorities){
                http.authorizeRequests().antMatchers(menu.getLink()).hasAuthority(auth.getRole());
            }
        }

        http
                .authorizeRequests()
                .antMatchers(resources).permitAll()
                .antMatchers("/","/index","/sigup","/message").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/menu")
                .failureUrl("/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(new TimeOutAuthenticationSuccessHandler())
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/login?logout");
    }
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
        return bCryptPasswordEncoder;
    }

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService (userDetailsService).passwordEncoder(passwordEncoder());
    }
}