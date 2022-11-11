package br.com.global.takeYourCar.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.global.takeYourCar.dto.UserDto;

@Entity
@Table(name = "TB_User")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String email;

    @NotBlank
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Role> roles = new ArrayList<>();

    public UserDto toDto() {
        return new UserDto(id, nome, email);
    }

    public User nome(String nome) {
        Assert.notNull(nome, "nome is required");
        this.nome = nome;
        return this;
    }

    public User email(String email) {
        Assert.notNull(email, "E-mail is required");
        this.email = email;
        return this;
    }

    public User password(String password) {
        Assert.notNull(password, "password is required");
        this.password = password;
        return this;
    }

    public User withRole(Role role) {
        Assert.notNull(role, "role is required");
        this.roles.add(role);
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getUsername() {
        return this.email;
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

    @Override
    public String toString() {
        return "User [id=" + id + ", nome=" + nome + ", email=" + email + ", password=" + password + ", roles=" + roles
                + "]";
    }

}
