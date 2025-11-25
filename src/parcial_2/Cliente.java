package parcial_2;

import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Cliente extends Usuario {
	
		protected LinkedList <Cuenta> cuentas;

		public Cliente(String nombre, String apellido, String dni, String contrasena) {
			super(nombre, apellido, dni, contrasena);
			this.cuentas = new LinkedList<Cuenta>();
		}
		
		public LinkedList<Cuenta> getCuentas() {
			return cuentas;
		}

		public void setCuentas(LinkedList<Cuenta> cuentas) {
			this.cuentas = cuentas;
		}

		@Override
		public String toString() {
			return "Cuentas= " + cuentas;
		}

		@Override
		public void menu() {
			String [] opciones = {"Ver cuentas", "Seleccionar cuenta", "Salir"};
			int seleccion;
			do {
				seleccion = JOptionPane.showOptionDialog(null, "Opciones","", 0,0, null, opciones, opciones[0]);
				switch (seleccion) {
				case 0:
					if (this.cuentas.isEmpty()) {
						JOptionPane.showMessageDialog(null, "No posee cuentas");
					} else {
						JOptionPane.showMessageDialog(null, toString());
					}
					break;
				case 1:
					//Ver como hacer para que cada cuenta sea una opcion, unir github con eclipse asi agilizamos consultas a gemini
					break;
				case 2:
					
					break;
				}
			} while (condition);
		}
		
		
}