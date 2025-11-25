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
				    String aliasNuevo = JOptionPane.showInputDialog("Ingrese el alias para la nueva cuenta:");
				    while (chequearAlias(aliasNuevo) == true) {
				    	aliasNuevo = JOptionPane.showInputDialog("Alias en uso, ingrese un alias diferente para la nueva cuenta:");
					}
				    TipoCuenta[] opcionesCuenta = TipoCuenta.values(); 
				    int seleccionCuenta = JOptionPane.showOptionDialog(null, 
				            "Seleccione tipo de cuenta", 
				            "Crear Cuenta", 
				            0, 0, null, 
				            opcionesCuenta,     
				            opcionesCuenta[0]); 
				    
				    TipoCuenta tipoElegido = opcionesCuenta[seleccionCuenta];
				    if (tipoElegido == TipoCuenta.CA) {
				        CajaAhorro nuevaCA = new CajaAhorro(aliasNuevo, this, TipoCuenta.CA);
				        this.cuentas.add(nuevaCA);
				        JOptionPane.showMessageDialog(null, "Caja de Ahorro creada con éxito!");
				    } else if (tipoElegido == TipoCuenta.CC) {				        
				        CuentaCorriente nuevaCC = new CuentaCorriente(aliasNuevo, this, TipoCuenta.CC);
				        this.cuentas.add(nuevaCC);
				        JOptionPane.showMessageDialog(null, "Cuenta Corriente creada con éxito!");
				    }
				   return menu(); 
				case 3:
					return 0;
				}
			} while (seleccion != 3);
			return 0;
		}
		
		public static boolean chequearAlias(String aliasChequear) {
		    for (Usuario usuario : Usuario.getUsuarios()) {
		        if (usuario.getRol() == RolUsuario.CLIENTE) {     
		            Cliente cliente = (Cliente) usuario;		            
		            for (Cuenta cuenta : cliente.getCuentas()) {
		                if (cuenta.getAlias().equals(aliasChequear)) {
		                    return true;
		                }
		            }
		        }
		    }
		    return false; // Nadie tiene este alias
		}
		
		
}