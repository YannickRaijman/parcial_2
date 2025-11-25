package parcial_2;

public class Admin extends Usuario {
	
	public Admin(String nombre, String apellido, String dni, String contrasena) {
		super(nombre, apellido, dni, contrasena);
		this.rol = RolUsuario.ADMIN;
	}
	
	
}
