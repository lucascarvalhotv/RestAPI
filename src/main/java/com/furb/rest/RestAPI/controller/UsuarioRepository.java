
package com.furb.rest.RestAPI.controller;

  
import com.furb.rest.RestAPI.model.Usuario;  
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

        Usuario findByLogin(String login);
}