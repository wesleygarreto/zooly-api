package br.com.truvainfo.zoolyapi.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class MyUserDetails implements UserDetails {

    private String email;
    private String password;
    private boolean active;
    private GrantedAuthority role;

    public MyUserDetails(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.active = user.isActive();
        this.role = new SimpleGrantedAuthority(user.getRole().getDesc());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.active ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.active ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.active ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean isEnabled() {
        return this.active ? Boolean.TRUE : Boolean.FALSE;
    }
}
