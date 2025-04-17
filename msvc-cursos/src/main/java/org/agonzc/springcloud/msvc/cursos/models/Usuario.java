package org.agonzc.springcloud.msvc.cursos.models;

public class Usuario {
	private Long id;

	private String nombre;

	private String email;

	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Usuario))
			return false;

		Usuario usuario = (Usuario) obj;
		return usuario.getId() != null && this.getId().equals(usuario.getId());
	}

}
