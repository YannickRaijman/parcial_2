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
			return "Cuentas= " + this.cuentas;
		}

		@Override
		public int menu() {
			String [] opciones = {"Ver cuentas", "Seleccionar cuenta", "Crear cuenta", "Salir"};
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
					return 0;
				case 1:
					if (!this.cuentas.isEmpty()) {
						int tamanoArray = this.cuentas.size();
						Cuenta [] arrayCuentas = this.cuentas.toArray(new Cuenta [tamanoArray]);
						int cuentaElegida = (int)JOptionPane.showInputDialog(null, "Seleccione la cuenta:", "", 0, null, arrayCuentas, arrayCuentas);
						return cuentaElegida;
					} else {
						JOptionPane.showMessageDialog(null, "No posee cuentas");
						return 0;
					}
				case 2:
					
				case 3:
					return 0;
				}
			} while (seleccion != 3);
			return 0;
		}
		
		
}