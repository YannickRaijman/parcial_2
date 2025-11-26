package parcial_2;

import java.time.LocalDate;

public class Movimiento {
	protected LocalDate fecha;
	protected TipoMovimiento tipo;
	protected double monto;
	
	public Movimiento(TipoMovimiento tipo, double monto) {
		this.fecha = LocalDate.now();
		this.tipo = tipo;
		this.monto = monto;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public TipoMovimiento getTipo() {
		return tipo;
	}

	public void setTipo(TipoMovimiento tipo) {
		this.tipo = tipo;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	@Override
	public String toString() {
		return fecha.toString() + "  " + tipo + "  $" + monto + "\n";
	}
	
	
}
