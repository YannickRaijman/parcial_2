package parcial_2;

import java.util.LinkedList;

import javax.swing.JOptionPane;

public abstract class Usuario {
	protected String nombre;
	protected String apellido;
	protected String dni;
	protected String contrasena;
	protected RolUsuario rol;
	protected static LinkedList <Usuario> usuarios = new LinkedList<Usuario>();
	
	public Usuario(String nombre, String apellido, String dni, String contrasena) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.contrasena = contrasena;
		this.rol = RolUsuario.CLIENTE;
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	public RolUsuario getRol() {
		return rol;
	}
	public void setRol(RolUsuario rol) {
		this.rol = rol;
	}
	public static LinkedList<Usuario> getUsuarios() {
		return usuarios;
	}
	public static void setUsuarios(LinkedList<Usuario> usuarios) {
		Usuario.usuarios = usuarios;
	}
	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", contrasena=" + contrasena
				+ ", rol=" +  "]";
	}
	
	public static Usuario login () {
		String [] opciones = {"Iniciar sesion", "Crear cuenta", "Salir"};
		int seleccion;
		do {
			seleccion = JOptionPane.showOptionDialog(null, "Bienvenido!","", 0,0, null, opciones, opciones[1]);
			switch (seleccion) {
			case 0:
				String dni = validarString("Ingrese el DNI");
				String contrasena = validarString("Ingrese la contraseña:");
				for (Usuario usuario: usuarios) {
					if (usuario.getDni().equals(dni) && usuario.getContrasena().equals(contrasena) ) {
						if (usuario.getRol().equals(RolUsuario.CLIENTE)) {
							return (Cliente) usuario;
						}else if (usuario.getRol().equals(RolUsuario.EMPLEADO)) {
							return (Empleado) usuario;
						} else if (usuario.getRol().equals(RolUsuario.ADMIN)) {
							return (Admin) usuario;
						}
					}
				}
				JOptionPane.showMessageDialog(null, "Usuario no encontrado");
				break;
			case 1:
				 return Usuario.crearUsuario();
			case 2:
				return null;
			} 
		} while (seleccion != 2);
		return null;
	}
	
	public static Usuario crearUsuario() {
	    String nombre = validarString("Ingrese el nombre:");
	    String apellido = validarString("Ingrese el apellido:");
	    String dni = validarString("Ingrese el DNI:");
	    String contrasena = validarString("Ingrese la contraseña:");

	    for (Usuario usuario : usuarios) {
	        if (usuario.getDni().equals(dni)) {
	            JOptionPane.showMessageDialog(null, "El usuario ya existe.");
	            return login();
	        }
	    }
	    Usuario nuevo = new Cliente(nombre, apellido, dni, contrasena);
	    usuarios.add(nuevo);
	    JOptionPane.showMessageDialog(null, "Usuario creado correctamente.");
	    return nuevo;
	}

	public static String validarString(String pedir) {
	    String valor = JOptionPane.showInputDialog(pedir);
	    while (valor.isEmpty()) {
	        valor = JOptionPane.showInputDialog("ERROR, " + pedir);
	    }
	    return valor;
	}
	
	public int menu () {
		//menu
		return 0;
	}
	
	
}
