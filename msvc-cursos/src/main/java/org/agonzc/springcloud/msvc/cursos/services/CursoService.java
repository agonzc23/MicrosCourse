package org.agonzc.springcloud.msvc.cursos.services;

import java.util.List;
import java.util.Optional;

import org.agonzc.springcloud.msvc.cursos.models.entities.Curso;

public interface CursoService {
	List<Curso> findAll();

	Optional<Curso> findById(Long id);

	Curso save(Curso user);

	void delete(Long id);
}
