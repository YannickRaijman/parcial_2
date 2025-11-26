package parcial_2;

import javax.swing.JOptionPane;

public class Empleado extends Usuario {

	public Empleado(String nombre, String apellido, String dni, String contrasena) {
		super(nombre, apellido, dni, contrasena);
		this.rol = RolUsuario.EMPLEADO;
	}
		
	@Override
    public void menu(Cajero cajero) {
        String[] opciones = {"Ver dinero disponible", "Cargar Cajero", "Cambiar Estado", "Ver Movimientos Cajero","Salir"};
        int seleccion;
        
        do {
            seleccion = JOptionPane.showOptionDialog(null, 
                    "Panel Empleado: " + this.nombre, 
                    "Opciones Cajero", 
                    0, 0, null, opciones, opciones[0]);

            switch (seleccion) {
            case 0:
                JOptionPane.showMessageDialog(null, "Dinero en cajero: $" + cajero.getDineroDisp());
                break;
            case 1:
                cajero.cargarCajero(); 
                JOptionPane.showMessageDialog(null, "El cajero ha sido recargado al máximo.");
                break;
            case 2:
                cajero.cambiarEstadoCajero();
                JOptionPane.showMessageDialog(null, "Estado actualizado a: " + cajero.getEstado());
                break;
            case 3:
                JOptionPane.showMessageDialog(null, cajero.getMovimientosCajero());
                break;
            case 4:
            }
        } while (seleccion != 4);
    }

}
