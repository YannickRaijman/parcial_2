package finalPOO;

import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Cajero {
	protected double dineroDisp;
	protected EstadoCajero estado;
	protected LinkedList <Movimiento> movimientosCajero;
	
	public Cajero() {
		this.dineroDisp = 1000000;
		this.estado = EstadoCajero.DISPONIBLE;
		this.movimientosCajero = new LinkedList <Movimiento>();
	}

	public double getDineroDisp() {
		return dineroDisp;
	}

	public void setDineroDisp(double dineroDisp) {
		this.dineroDisp = dineroDisp;
	}

	public EstadoCajero getEstado() {
		return estado;
	}

	public void setEstado(EstadoCajero estado) {
		this.estado = estado;
	}
	
	public LinkedList<Movimiento> getMovimientosCajero() {
		return movimientosCajero;
	}

	public void setMovimientosCajero(LinkedList<Movimiento> movimientosCajero) {
		this.movimientosCajero = movimientosCajero;
	}

	@Override
	public String toString() {
		return "Cajero [dineroDisp=" + dineroDisp + ", estado=" + estado + "]";
	}
	
	public void cargarCajero() {
		this.setDineroDisp(1000000);
		this.movimientosCajero.add(new Movimiento(TipoMovimiento.CARGADECAJERO, 1000000));
	}
	public void cambiarEstadoCajero() {
		EstadoCajero [] opciones = EstadoCajero.values();
		int seleccion;
		seleccion = JOptionPane.showOptionDialog(null, "Seleccione el estado del cajero","", 0,0, null, opciones, opciones[0]);
		switch (seleccion) {
		case 0:
			this.estado = EstadoCajero.DISPONIBLE;
			break;
		case 1:
			this.estado = EstadoCajero.FUERADESERVICIO;
			break;
		}
	}
	
	public void depositarDinero(Cuenta cuentaCliente, double monto) {
        if (this.estado == EstadoCajero.FUERADESERVICIO) {
            JOptionPane.showMessageDialog(null, "El cajero está fuera de servicio.");
            return;
        }
        if (monto <= 0) {
            JOptionPane.showMessageDialog(null, "El monto debe ser positivo.");
            return;
        }
        this.dineroDisp += monto;
        this.movimientosCajero.add(new Movimiento(TipoMovimiento.DEPOSITO, monto));
        cuentaCliente.depositar(monto); 
        JOptionPane.showMessageDialog(null, "Depósito realizado con éxito.");
    }
	
	public void extraerDinero(Cuenta cuentaCliente, double monto) {
        if (this.estado == EstadoCajero.FUERADESERVICIO) {
            JOptionPane.showMessageDialog(null, "El cajero está fuera de servicio.");
            return;
        }
        if (this.dineroDisp < monto) {
            JOptionPane.showMessageDialog(null, "ERROR: El cajero no posee suficiente dinero.");
        } else {
            if (cuentaCliente.retirar(monto)) {
                this.dineroDisp -= monto;
                this.movimientosCajero.add(new Movimiento(TipoMovimiento.RETIRO, monto));
                JOptionPane.showMessageDialog(null, "Retire su dinero.");
            }
        }
    }
	
}
