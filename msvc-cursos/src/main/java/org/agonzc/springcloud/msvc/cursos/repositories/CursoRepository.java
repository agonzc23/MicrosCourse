package org.agonzc.springcloud.msvc.cursos.repositories;

import org.agonzc.springcloud.msvc.cursos.models.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CursoRepository extends JpaRepository<Curso, Long>{

	@Modifying
	@Query("delete from CursoUsuario cu where cu.usuarioId=?1")
	void deleteCursoUsuario(Long id);
}
