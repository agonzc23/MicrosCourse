package org.agonzc.springcloud.msvc.usuarios.controllers;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.agonzc.springcloud.msvc.usuarios.models.entities.Usuario;
import org.agonzc.springcloud.msvc.usuarios.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@GetMapping
	public List<Usuario> getAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		Optional<Usuario> opUsr = service.findById(id);
		if (opUsr.isPresent())
			return ResponseEntity.ok(opUsr.get());
		else
			return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody Usuario user, BindingResult result) {
		if (result.hasErrors()) {
			return validateErrors(result);
		}

		if (service.existByEmail(user.getEmail())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(Collections.singletonMap("mail", "Ya existe un usuario con ese email"));
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Usuario user, BindingResult result, @PathVariable Long id) {
		if (result.hasErrors()) {
			return validateErrors(result);
		}

		Optional<Usuario> opUser = service.findById(id);
		if (opUser.isPresent()) {
			Usuario dbUser = opUser.get();
			dbUser.setNombre(user.getNombre());
			if (user.getEmail() != dbUser.getEmail() && service.existByEmail(user.getEmail())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(Collections.singletonMap("mail", "Ya existe un usuario con ese email"));
			}
			dbUser.setEmail(user.getEmail());
			dbUser.setPassword(user.getPassword());
			return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dbUser));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Optional<Usuario> opUser = service.findById(id);
		if (opUser.isPresent()) {
			service.delete(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	private ResponseEntity<?> validateErrors(BindingResult result) {
		Map<String, String> errores = new HashMap<>();
		result.getFieldErrors().forEach(err -> {
			errores.put(err.getField(), err.getDefaultMessage());
		});
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
	}
}