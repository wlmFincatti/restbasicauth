package br.com.wfincatti.basicauth.domain.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class User implements UserDetails {

    private static final long serialVersionUID = -329762499685651382L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<Role> roles;
    private Boolean enabled;
    private Boolean credentialsNonLocked;
    private Boolean acountNonExpired;
    private Boolean acountNonLocked;

    public User() {
        this.enabled = true;
        this.credentialsNonLocked = true;
        this.acountNonExpired = true;
        this.acountNonLocked = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.toString())));
        return authorities;
    }

    public void grantAuthority(Role authority) {
        if (ObjectUtils.isEmpty(roles)) roles = new ArrayList<>();
        roles.add(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return acountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return acountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonLocked;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
