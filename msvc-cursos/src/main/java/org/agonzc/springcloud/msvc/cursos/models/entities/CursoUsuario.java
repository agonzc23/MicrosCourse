package org.agonzc.springcloud.msvc.cursos.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cursos_usuarios")
public class CursoUsuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(name = "usuario_id", unique = true)
	private Long usuarioId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof CursoUsuario))
			return false;
		
		CursoUsuario cursoUsuario = (CursoUsuario) obj;
		return cursoUsuario.getUsuarioId() != null && this.getUsuarioId().equals(cursoUsuario.getUsuarioId());
	}
}
