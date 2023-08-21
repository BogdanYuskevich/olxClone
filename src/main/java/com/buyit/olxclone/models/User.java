package com.buyit.olxclone.models;


import com.buyit.olxclone.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements UserDetails {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phone;
    @Column(name = "name")
    private String name;
    @Column(name = "isActive")
    private boolean isActive;
    @JoinColumn(name = "image_id")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Imagine imagine;
    @Column(name = "password",length = 1000)
    private String password;
    @Column(name = "roleSet")
    @ElementCollection(targetClass = Role.class,fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",
    joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roleSet = new HashSet<>();
    @Column(name = "localTime")
    private LocalDateTime localDateTime;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private List<Product> products = new ArrayList<>();

    @PrePersist
    private void init() {
        localDateTime = LocalDateTime.now();
    }

    //security

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleSet;
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
        return isActive;
    }
}
