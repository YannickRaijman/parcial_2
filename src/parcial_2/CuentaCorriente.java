package parcial_2;


import javax.swing.JOptionPane;

public class CuentaCorriente extends Cuenta{
	protected double limite;


	public CuentaCorriente(String alias, Cliente titular, TipoCuenta tipo) {
		super(alias, titular, tipo);
		this.limite = 1000000;
	}
	
	@Override
	public boolean retirar(double monto) {
	    if ((this.saldo + this.limite) >= monto) {
	        this.saldo -= monto;
	        this.movimientos.add(new Movimiento(TipoMovimiento.RETIRO, monto));
	        return true;
	    } else {
	        JOptionPane.showMessageDialog(null,"ERROR, fondos insuficientes (supera el límite de descubierto)");
	        return false;
	    }
	}
	

}
