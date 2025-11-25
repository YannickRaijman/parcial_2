package parcial_2;

public class Empleado extends Usuario {

	public Empleado(String nombre, String apellido, String dni, String contrasena) {
		super(nombre, apellido, dni, contrasena);
		this.rol = RolUsuario.EMPLEADO;
	}
	

}
