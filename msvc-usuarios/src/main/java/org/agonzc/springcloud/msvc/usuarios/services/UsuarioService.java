package org.agonzc.springcloud.msvc.usuarios.services;

import java.util.List;
import java.util.Optional;

import org.agonzc.springcloud.msvc.usuarios.models.entities.Usuario;

public interface UsuarioService {

	List<Usuario> findAll();

	List<Usuario> findAllByIds(Iterable<Long> ids);
	
	Optional<Usuario> findById(Long id);

	Usuario save(Usuario user);

	void delete(Long id);

	boolean existByEmail(String email);
}
