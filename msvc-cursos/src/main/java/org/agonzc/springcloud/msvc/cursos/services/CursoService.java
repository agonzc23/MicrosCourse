package org.agonzc.springcloud.msvc.cursos.services;

import java.util.List;
import java.util.Optional;

import org.agonzc.springcloud.msvc.cursos.models.Usuario;
import org.agonzc.springcloud.msvc.cursos.models.entities.Curso;

public interface CursoService {
	List<Curso> findAll();

	Optional<Curso> findById(Long id);

	Curso save(Curso user);

	void delete(Long id);
	
	void unassignByUserId(Long userId);
	
	Optional<Usuario> assignUser(Long usuarioId, Long cursoId);
	
	Optional<Usuario> unassignUser(Long userId, Long cursoId);
}
