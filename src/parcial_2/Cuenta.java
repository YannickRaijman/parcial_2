package parcial_2;

import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Cuenta {
	protected double saldo;
	protected String alias;
	protected LinkedList <Movimiento> movimientos;
	protected Cliente titular;
	protected TipoCuenta tipo;

	public Cuenta(String alias, Cliente titular, TipoCuenta tipo) {
		this.saldo = 0;
		this.alias = alias;
		this.movimientos = new LinkedList <Movimiento>();
		this.titular = titular;
		this.tipo = tipo;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public LinkedList<Movimiento> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(LinkedList<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	public Cliente getTitular() {
		return titular;
	}

	public void setTitular(Cliente titular) {
		this.titular = titular;
	}
		
	public TipoCuenta getTipo() {
		return tipo;
	}

	public void setTipo(TipoCuenta tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Saldo= $" + saldo + " Alias= " + alias + " Tipo= " + tipo + "\n";
	}
	
	public boolean retirar (double monto) {
		//retirar dinero
		return true;
	}
	
	public void depositar(Cajero cajero) {
		if (cajero.getEstado() == EstadoCajero.FUERADESERVICIO) {
			JOptionPane.showMessageDialog(null, "El cajero no se puede usar porque esta fuera de servicio");
		} else {		
		    double monto = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto a depositar: "));
		    while (monto <= 0) {
		    	monto = Double.parseDouble(JOptionPane.showInputDialog("Error, Ingrese el monto a depositar: "));
			}
		    	cajero.setDineroDisp(cajero.getDineroDisp() + monto);
		        this.saldo += monto;
		        this.movimientos.add(new Movimiento(TipoMovimiento.DEPOSITO, monto));
		}
	}
	
	public boolean transferir(Cuenta destino, double monto) {
	    if (this.retirar(monto)) {
	        if (!this.movimientos.isEmpty()) {
	            this.movimientos.getLast().setTipo(TipoMovimiento.TRANSFERENCIA);
	        }

	        // 3. Acreditamos en la cuenta DESTINO (Lógica virtual)
	        destino.depositar(monto);
	        
	        // 4. Corregimos la etiqueta en el destino
	        if (!destino.getMovimientos().isEmpty()) {
	            destino.getMovimientos().getLast().setTipo(TipoMovimiento.TRANSFERENCIA);
	        }

	        return true; // Éxito
	    }
	    return false; // Falló el retiro (saldo insuficiente)
	}
	
}
