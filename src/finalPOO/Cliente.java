package finalPOO;

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
		public void menu(Cajero cajero) {
			String [] opciones = {"Ver cuentas", "Seleccionar cuenta", "Crear cuenta", "Salir"};
			int seleccion;
			do {
				seleccion = JOptionPane.showOptionDialog(null, "Bienvenido " + this.nombre,"", 0,0, null, opciones, opciones[0]);
				switch (seleccion) {
				case 0:
					if (this.cuentas.isEmpty()) {
						JOptionPane.showMessageDialog(null, "No posee cuentas");
					} else {
						JOptionPane.showMessageDialog(null, toString());
					}
					break;
				case 1:
			        if (!this.cuentas.isEmpty()) {
			            Cuenta[] arrayCuentas = this.cuentas.toArray(new Cuenta[0]);
			            Cuenta cuentaElegida = (Cuenta) JOptionPane.showInputDialog(null, "Seleccione la cuenta:", "Mis cuentas", 0, null, arrayCuentas, arrayCuentas[0]);
			            if (cuentaElegida != null) {
			                menuCuenta(cuentaElegida, cajero); 
			            }
			        } else {
			            JOptionPane.showMessageDialog(null, "No posee cuentas");
			        }
			         break;
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
				   break;
				case 3:
				}
			} while (seleccion != 3);
		}
		
		public static boolean chequearAlias(String aliasChequear) {
			if (aliasChequear == null || aliasChequear.isEmpty()) {
				return true;
			}
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
		public void menuCuenta(Cuenta cuentaSeleccionada, Cajero cajero) {
	        String[] opciones = {"Ver Saldo", "Depositar", "Extraer", "Transferir", "Ver Movimientos", "Volver"};
	        int seleccion;
	        do {
	            seleccion = JOptionPane.showOptionDialog(null, 
	                    "Cuenta: " + cuentaSeleccionada.getAlias(), 
	                    "Operaciones", 0, 0, null, opciones, opciones[0]);
	            switch (seleccion) {
	                case 0:
	                    JOptionPane.showMessageDialog(null, "Saldo actual: $" + cuentaSeleccionada.getSaldo());
	                    break;
	                case 1:
	                    realizarDeposito(cuentaSeleccionada, cajero);
	                    break;
	                case 2:
	                    realizarExtraccion(cuentaSeleccionada, cajero);
	                    break;
	                case 3:
	                    realizarTransferencia(cuentaSeleccionada);
	                    break;
	                case 4:
	                    mostrarMovimientos(cuentaSeleccionada);
	                    break;
	                case 5:
	                    return;
	            }
	        } while (seleccion != 5);
	    }
		
		private void realizarDeposito(Cuenta cuenta, Cajero cajero) {
	        String ingreso = JOptionPane.showInputDialog("Monto a depositar:");
	        if (esNumero(ingreso)) {
	            double monto = Double.parseDouble(ingreso);
	            cajero.depositarDinero(cuenta, monto);
	        } else {
	            JOptionPane.showMessageDialog(null, "Monto inválido");
	        }
	    }
		
		private boolean esNumero(String dato) {
	        if (dato == null || dato.isEmpty()) return false;
	        int puntos = 0;
	        for (int i = 0; i < dato.length(); i++) {
	            char c = dato.charAt(i);
	            if (c == '.') {
	                puntos++;
	                if (puntos > 1) return false;
	            } else if (!Character.isDigit(c)) {
	                return false;
	            }
	        }
	        return true;
	    }
		
		private void realizarExtraccion(Cuenta cuenta, Cajero cajero) {
	        String ingreso = JOptionPane.showInputDialog("Monto a extraer:");
	        if (esNumero(ingreso)) {
	            double monto = Double.parseDouble(ingreso);
	            cajero.extraerDinero(cuenta, monto);
	        } else {
	            JOptionPane.showMessageDialog(null, "Monto inválido");
	        }
	    }
		
		private Cuenta buscarCuenta(String alias) {
	        for (Usuario usuario : Usuario.getUsuarios()) {
	            if (usuario.getRol() == RolUsuario.CLIENTE) {
	                Cliente cliente = (Cliente) usuario;
	                for (Cuenta cuenta : cliente.getCuentas()) {
	                    if (cuenta.getAlias().equals(alias)) {
	                        return cuenta;
	                    }
	                }
	            }
	        }
	        return null;
	    }
		
		private void realizarTransferencia(Cuenta origen) {
	        String aliasDestino = JOptionPane.showInputDialog("Alias destino:");
	        Cuenta destino = buscarCuenta(aliasDestino);
	        if (destino == null) {
	            JOptionPane.showMessageDialog(null, "Cuenta no encontrada.");
	            return;
	        }
	        if (destino == origen) {
	            JOptionPane.showMessageDialog(null, "No podes transferirte a la misma cuenta.");
	            return;
	        }
	        String ingreso = JOptionPane.showInputDialog("Monto a transferir:");
	        if (esNumero(ingreso)) {
	            double monto = Double.parseDouble(ingreso);
	            if (origen.transferir(destino, monto)) {
	                JOptionPane.showMessageDialog(null, "Transferencia exitosa!");
	            }
	        } else {
	            JOptionPane.showMessageDialog(null, "Monto inválido");
	        }
	    }
		
		private void mostrarMovimientos(Cuenta cuenta) {
	        if (cuenta.getMovimientos().isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Sin movimientos.");
	        } else {
	            	JOptionPane.showMessageDialog(null, cuenta.getMovimientos());
	        }
	    }
		
		
		
		
		
}