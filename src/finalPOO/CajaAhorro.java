package finalPOO;


import javax.swing.JOptionPane;

public class CajaAhorro extends Cuenta {
	
	public CajaAhorro(String alias, Cliente titular, TipoCuenta tipo) {
		super(alias, titular, tipo);
	}

	@Override
	public boolean retirar(double monto) {
		 	if ((this.saldo - monto) >= 0) {
				this.saldo -= monto;
				this.movimientos.add(new Movimiento(TipoMovimiento.RETIRO, monto));
				return true;
			} else {
				JOptionPane.showMessageDialog(null,"ERROR, el monto a retirar es mayor al saldo de la cuenta");
				return false;
			}
	}
	
	
}
