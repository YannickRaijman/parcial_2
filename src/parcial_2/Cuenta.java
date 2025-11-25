package parcial_2;

import java.util.LinkedList;

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
	
}
