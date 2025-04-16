package org.agonzc.springcloud.msvc.cursos.repositories;

import org.agonzc.springcloud.msvc.cursos.models.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long>{

}
