package finalPOO;

import java.time.LocalDateTime;

public class Movimiento {
	protected LocalDateTime fecha;
	protected TipoMovimiento tipo;
	protected double monto;
	
	public Movimiento(TipoMovimiento tipo, double monto) {
		this.fecha = LocalDateTime.now();
		this.tipo = tipo;
		this.monto = monto;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
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
