package br.com.wepdev.microservicoautenticacao.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User implements Serializable, UserDetails {
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "user_name", unique = true)
	private String userName;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "accountNonExpired")
	private Boolean accountNonExpired;
	
	@Column(name = "accountNonLocked")
	private Boolean accountNonLocked;
	
	@Column(name = "credentialsNonExpired")
	private Boolean credentialsNonExpired;
	
	@Column(name = "enabled")
	private Boolean enabled;
	
	@ManyToMany(fetch = FetchType.EAGER)// Muitos usuarios possuem varias permissoes
	@JoinTable(name = "user_permission", joinColumns = {@JoinColumn(name = "id_user")},
	inverseJoinColumns = {@JoinColumn(name = "id_permissions")} )
	private List<Permission> permissoes;
	
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.permissoes;
	}
	
	
	public List<String> getRoles(){
		List<String> roles = new ArrayList<>();
		this.permissoes.stream()
		               .forEach( p -> {
			                roles.add(p.getDescription()); // Para cada permissão vai ser adicionado sera adicionado a descrição a lista que sera retornada
		});
		return roles;
	}

	
	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

}
