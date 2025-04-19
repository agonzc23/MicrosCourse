package org.agonzc.springcloud.msvc.usuarios.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-cursos", url = "host.docker.internal:8082")
public interface CursosClientRest {

	@DeleteMapping("/user/{userId}")
	public void unassignUserId(@PathVariable Long userId);
}
