package com.cibertec.gestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.cibertec.gestion.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	@Query(value = "SELECT COUNT(*) FROM usuarios WHERE email = ?1 AND password = ?2", nativeQuery = true)
    Long findUserToLogin(String username, String password);
}
