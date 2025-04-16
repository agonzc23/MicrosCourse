package org.agonzc.springcloud.msvc.cursos.services;

import java.util.List;
import java.util.Optional;

import org.agonzc.springcloud.msvc.cursos.models.entities.Curso;
import org.agonzc.springcloud.msvc.cursos.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CursoServiceImpl implements CursoService{

	@Autowired CursoRepository cursoRepo;
	
	@Override
	@Transactional(readOnly = true)
	public List<Curso> findAll() {
		return cursoRepo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Curso> findById(Long id) {
		return cursoRepo.findById(id);
	}

	@Override
	@Transactional
	public Curso save(Curso user) {
		return cursoRepo.save(user);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		cursoRepo.deleteById(id);
	}

}
