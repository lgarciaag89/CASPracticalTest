package com.casystems.caspracticaltest.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

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

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(obj == this)
            return true;
        if(!(obj instanceof UserDetail))
            return true;
        UserDetail userDetail = (UserDetail) obj;
        if(!super.equals(userDetail))
            return false;
        if(timeout!=userDetail.getTimeout())
            return false;
        return true;
    }
}
