package com.casystems.caspracticaltest.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=true)
public class UserDetail extends User {
    private Integer timeout;
    public UserDetail(String username, String password, Collection<? extends GrantedAuthority> authorities, Integer time) {
        super(username, password, authorities);
        timeout = time;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
