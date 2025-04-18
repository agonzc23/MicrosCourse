package org.agonzc.springcloud.msvc.usuarios.repositories;

import java.util.Optional;

import org.agonzc.springcloud.msvc.usuarios.models.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Optional<Usuario> findByEmail(String email);

}
