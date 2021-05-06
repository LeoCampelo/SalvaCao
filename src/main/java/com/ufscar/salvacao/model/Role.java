package com.ufscar.salvacao.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
@Entity
@Table(name="role")
public class Role implements GrantedAuthority{
    
    @Id
    @Column(name="nome_role")
    private String nomeRole;

    @ManyToMany
    private List<Usuario> usuarios;

    @Override
    public String getAuthority() {
        return this.nomeRole;
    }
}
