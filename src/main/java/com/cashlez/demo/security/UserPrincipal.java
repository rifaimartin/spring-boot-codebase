//
//package com.cashlez.demo.security;
//
//import com.cashlez.demo.model.Merchant;
//import com.enigma.entities.User;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//public class UserPrincipal implements UserDetails {
//    private Merchant merchant;
//
//    public UserPrincipal(Merchant user) {
//        this.merchant = merchant;
//    }
//
//    public Long getId(){
//        return merchant.getId();
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        //Extract list of Roles
////        this.user.getUserRoles().forEach(role -> {
////            authorities.add(new SimpleGrantedAuthority(role.getUserRoles().getLabel()));
////        });
//        return authorities;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return this.user.getIsActive();
//    }
//}
//
