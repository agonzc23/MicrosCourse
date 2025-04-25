package org.agonzc.springcloud.msvc.cursos.clients;

import java.util.List;

import org.agonzc.springcloud.msvc.cursos.models.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "msvc-usuarios", url = "${usuarios.service.url}")
public interface UsuarioClientRest {

	@GetMapping("/{id}")
	Usuario getById(@PathVariable Long id);
	
	@GetMapping
	List<Usuario> getAll();

	@GetMapping("/usuarios")
	List<Usuario> getAllById(@RequestParam List<Long> ids);
}
