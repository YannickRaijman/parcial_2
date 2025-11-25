package parcial_2;

import java.util.LinkedList;

import javax.swing.JOptionPane;

public class CuentaCorriente extends Cuenta{
	protected double limite;

	public CuentaCorriente(double saldo, String alias, LinkedList<Movimiento> movimientos, Cliente titular,
			TipoCuenta tipo, double limite) {
		super(saldo, alias, movimientos, titular, tipo);
		this.limite = limite;
	}

	@Override
	public boolean retirar(double monto) {
	 	if ((this.saldo - monto) < this.limite) {
			this.saldo -= monto;
			this.movimientos.add(new Movimiento(TipoMovimiento.RETIRO, monto));
			return true;
		} else {
			JOptionPane.showMessageDialog(null,"ERROR, el monto a retirar es mayor al limite permitido de la cuenta");
			return false;
		}
	}
	
	
	

}
