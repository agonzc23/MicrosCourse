package org.agonzc.springcloud.msvc.usuarios.services;

import java.util.List;
import java.util.Optional;

import org.agonzc.springcloud.msvc.usuarios.clients.CursosClientRest;
import org.agonzc.springcloud.msvc.usuarios.models.entities.Usuario;
import org.agonzc.springcloud.msvc.usuarios.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CursosClientRest cursosClient;
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Usuario> findById(Long id) {
		return usuarioRepository.findById(id);
	}

	@Override
	@Transactional
	public Usuario save(Usuario user) {
		return usuarioRepository.save(user);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		usuarioRepository.deleteById(id);
		cursosClient.unassignUserId(id);
	}

	@Override
	public boolean existByEmail(String email) {
		return usuarioRepository.findByEmail(email).isPresent();
	}

	@Override
	public List<Usuario> findAllByIds(Iterable<Long> ids) {
		return usuarioRepository.findAllById(ids);
	}

}
