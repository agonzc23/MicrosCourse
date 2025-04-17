package org.agonzc.springcloud.msvc.cursos.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.agonzc.springcloud.msvc.cursos.models.entities.Curso;
import org.agonzc.springcloud.msvc.cursos.services.CursoService;
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
public class CursoController {

	@Autowired
	CursoService cursoService;

	@GetMapping
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(cursoService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		Optional<Curso> opCurso = cursoService.findById(id);
		if (opCurso.isPresent())
			return ResponseEntity.ok(opCurso.get());
		else
			return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody Curso curso, BindingResult result) {
		if(result.hasErrors()) {
			return validateErrors(result);
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.save(curso));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Curso curso, BindingResult result) {
		if(result.hasErrors()) {
			return validateErrors(result);
		}
		
		Optional<Curso> opCurso = cursoService.findById(id);
		if (opCurso.isPresent()) {
			Curso dbCurso = opCurso.get();
			dbCurso.setNombre(curso.getNombre());
			return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.save(dbCurso));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Optional<Curso> opCurso = cursoService.findById(id);
		if (opCurso.isPresent()) {
			cursoService.delete(id);
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
