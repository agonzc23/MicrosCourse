package org.agonzc.springcloud.msvc.cursos.services;

import java.util.List;
import java.util.Optional;

import org.agonzc.springcloud.msvc.cursos.clients.UsuarioClientRest;
import org.agonzc.springcloud.msvc.cursos.models.Usuario;
import org.agonzc.springcloud.msvc.cursos.models.entities.Curso;
import org.agonzc.springcloud.msvc.cursos.models.entities.CursoUsuario;
import org.agonzc.springcloud.msvc.cursos.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CursoServiceImpl implements CursoService{

	@Autowired CursoRepository cursoRepo;
	
	@Autowired UsuarioClientRest usersClient;
	
	@Override
	@Transactional(readOnly = true)
	public List<Curso> findAll() {
		return cursoRepo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Curso> findById(Long id) {
		Optional<Curso> opCurso =  cursoRepo.findById(id);
		if (opCurso.isPresent()) {
			Curso curso = opCurso.get();
			List<Long> userIds = curso.getCursoUsuarios().stream().map(CursoUsuario::getUsuarioId).toList();
			
			usersClient.getAllById(userIds).forEach(user -> {
				curso.addUsuario(user);
			});
			
			return Optional.of(curso);
		}
		
		return Optional.empty();
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

	@Override
	@Transactional
	public Optional<Usuario> assignUser(Long usuarioId, Long cursoId) {
		if(cursoRepo.findById(cursoId).isPresent()) {
			Usuario userM = usersClient.getById(usuarioId);
		
			Curso curso = cursoRepo.findById(cursoId).get();
			
			CursoUsuario cursoUsuario = new CursoUsuario();
			cursoUsuario.setUsuarioId(userM.getId());
			curso.addCursoUsuario(cursoUsuario);
			
			cursoRepo.save(curso);
			return Optional.of(userM);
		}
		return Optional.empty();
	}

	@Override
	@Transactional
	public Optional<Usuario> unassignUser(Long usuarioId, Long cursoId) {
		if(cursoRepo.findById(cursoId).isPresent()) {
			Usuario userM = usersClient.getById(usuarioId);
		
			Curso curso = cursoRepo.findById(cursoId).get();
			
			CursoUsuario cursoUsuario = new CursoUsuario();
			cursoUsuario.setUsuarioId(userM.getId());
			curso.removeCursoUsuario(cursoUsuario);
			
			cursoRepo.save(curso);
			return Optional.of(userM);
		}
		return Optional.empty();
	}

	@Override
	@Transactional
	public void unassignByUserId(Long userId) {
		cursoRepo.deleteCursoUsuario(userId);
	}

}
