package com.sapphire.user.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Author: Ethan <br/>
 * Date: 2015/11/4.<br/>
 * Email: byp5303628@hotmail.com
 */
@Entity
@Table(name = User.TABLE_NAME)
public class User implements UserDetails {
   public static final String TABLE_NAME = "USER";

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "UIDPK")
   private long uidPk;

   @Basic
   @Column(name = "USERNAME")
   private String username;

   @Basic
   @Column(name = "PASSWORD")
   private String password;

   @Basic
   @Column(name = "EMAIL")
   private String email;

   @ManyToOne
   @JoinColumn(name = "ROLE_UID")
   private Role role;

   public Role getRole() {
      return role;
   }

   public void setRole(Role role) {
      this.role = role;
   }

   public long getUidPk() {
      return uidPk;
   }

   public void setUidPk(long uidPk) {
      this.uidPk = uidPk;
   }

   @Override
   public String getUsername() {
      return username;
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

   public void setUsername(String username) {
      this.username = username;
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      List<SimpleGrantedAuthority> authorities =
            new ArrayList<>();
      SimpleGrantedAuthority sim =
            new SimpleGrantedAuthority(this.getRole().getRoleName());
      authorities.add(sim);
      return authorities;
   }

   @Override
   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }
}
