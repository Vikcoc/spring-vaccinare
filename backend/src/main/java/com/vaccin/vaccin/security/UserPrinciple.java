package com.vaccin.vaccin.security;

import com.vaccin.vaccin.dto.AuthDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserPrinciple implements UserDetails {

    private String email;
    private Collection<? extends GrantedAuthority> authorities;

    public UserPrinciple(String email, Collection<? extends GrantedAuthority> authorities) {
        this.email=email;
        this.authorities=authorities;
    }

    public static UserPrinciple build(AuthDto authDto) {
        String email = authDto.getEmail();
        String role = authDto.getRole();
        List<GrantedAuthority> authorityList = new ArrayList<>();

//        if (email.equals("ana@cst.ro")) {
//            role = "ROLE_ADMIN";
//        } else {
//            role = "ROLE_DEFAULT";
//        }

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role);
        authorityList.add(simpleGrantedAuthority);

        return new UserPrinciple(email, authorityList);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
