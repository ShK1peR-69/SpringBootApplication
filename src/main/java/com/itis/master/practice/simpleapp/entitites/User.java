package com.itis.master.practice.simpleapp.entitites;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Set;

/*****
 * @author Igor Astafyev
 * June 2019
 * "Users" entity
 *****/

@Entity
@Table(name = "users")
public class User implements UserDetails {

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "user_seq", sequenceName = "users_id_seq",
			initialValue = 51, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_seq")
	private Long id;

	@Column(name = "fio")
	private String fio;

	@Column(name = "age")
	private int age;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "role")
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Role> roles;

	@Column(name = "approved")
	private boolean approved;

	@Column(name = "validkey")
	private String validkey;

	public User() {
	}

	public User(String fio, int age, String email, String password) {
		this.fio = fio;
		this.age = age;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public String getValidkey() {
		return validkey;
	}

	public void setValidkey(String validkey) {
		this.validkey = validkey;
	}

	public User(Long id, String fio, int age, String email, String password, Set<Role> roles, boolean approved, String validkey) {
		this.id = id;
		this.fio = fio;
		this.age = age;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.approved = approved;
		this.validkey = validkey;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getRoles();
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return isApproved();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String toString() {
		return "User{" +
		       "id=" + id +
		       ", fio='" + fio + '\'' +
		       ", age=" + age +
		       ", email='" + email + '\'' +
		       ", password='" + password + '\'' +
		       ", roles='" + roles.toString() + '\'' +
		       ", approved=" + approved +
		       ", validkey='" + validkey + '\'' +
		       '}';
	}
}