package com.sapphire.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

   @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
         CascadeType.MERGE, CascadeType.REFRESH })
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

   public String getUsername() {
      return username;
   }

   public boolean isAccountNonExpired() {
      return true;
   }

   public boolean isAccountNonLocked() {
      return true;
   }

   public boolean isCredentialsNonExpired() {
      return true;
   }

   public boolean isEnabled() {
      return true;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public Collection<? extends GrantedAuthority> getAuthorities() {
      List<SimpleGrantedAuthority> auths =
            new ArrayList<SimpleGrantedAuthority>();
      SimpleGrantedAuthority sim =
            new SimpleGrantedAuthority("ROLE_USER");
      auths.add(sim);
      return auths;
   }

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
