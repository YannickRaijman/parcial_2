package parcial_2;

import javax.swing.JOptionPane;

public class Admin extends Usuario {
	
	public Admin(String nombre, String apellido, String dni, String contrasena) {
		super(nombre, apellido, dni, contrasena);
		this.rol = RolUsuario.ADMIN;
	}
	
	@Override
    public void menu(Cajero cajero) {
        String[] opciones = {"Crear Empleado", "Eliminar Empleado", "Lista de Empleados", "Salir"};
        int seleccion;

        do {
            seleccion = JOptionPane.showOptionDialog(null, 
                    "Panel Administrador", 
                    "Gestión de Personal", 
                    0, 0, null, opciones, opciones[0]);
            switch (seleccion) {
            case 0: 
                crearEmpleado();
                break;
            case 1: 
                eliminarEmpleado();
                break;
            case 2: 
                listaDeEmpleados();
                break;
            case 3:
            }
        } while (seleccion != 3);
    }

    private void crearEmpleado() {
        String nombre = Usuario.validarString("Ingrese el nombre del empleado:");
        String apellido = Usuario.validarString("Ingrese el apellido:");
        String dni = Usuario.validarString("Ingrese el DNI:");
        if (buscarUsuario(dni) != null) {
            JOptionPane.showMessageDialog(null, "Error: Ya existe un usuario con ese DNI.");
            return;
        }
        String contrasena = Usuario.validarString("Ingrese contraseña:");
        Empleado nuevoEmp = new Empleado(nombre, apellido, dni, contrasena);
        Usuario.getUsuarios().add(nuevoEmp);
        JOptionPane.showMessageDialog(null, "Empleado creado exitosamente.");
    }

    private void eliminarEmpleado() {
        String dniBuscado = JOptionPane.showInputDialog("Ingrese el DNI del empleado a eliminar:");
        if (dniBuscado == null) return;
        Usuario aBorrar = buscarUsuario(dniBuscado);
        if (aBorrar != null) {
            if (aBorrar.getRol() == RolUsuario.EMPLEADO) {
                Usuario.getUsuarios().remove(aBorrar);
                JOptionPane.showMessageDialog(null, "Empleado eliminado.");
            } else {
                JOptionPane.showMessageDialog(null, "Error, el usuario con ese DNI no es un Empleado.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
        }
    }

    private void listaDeEmpleados() {
        String lista = "Lista de Empleados:\n";
        boolean hayEmpleados = false;
        for (Usuario usuario : Usuario.getUsuarios()) {
            if (usuario.getRol() == RolUsuario.EMPLEADO) {
                lista = lista + "- " + usuario.getNombre() + " " + usuario.getApellido() + " (DNI: " + usuario.getDni() + ")\n";
                hayEmpleados = true;
            }
        }
        if (!hayEmpleados) {
            JOptionPane.showMessageDialog(null, "No hay empleados registrados.");
        } else {
            JOptionPane.showMessageDialog(null, lista);
        }
    }

    private Usuario buscarUsuario(String dni) {
        for (Usuario usuario: Usuario.getUsuarios()) {
            if (usuario.getDni().equals(dni)) {
                return usuario;
            }
        }
        return null;
    }
}
