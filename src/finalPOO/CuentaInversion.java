package finalPOO;

import java.util.LinkedList;

import javax.swing.JOptionPane;

public class CuentaInversion extends Cuenta{
	
	private LinkedList<Inversion> inversiones;

    public CuentaInversion(String alias, Cliente titular, TipoCuenta tipo) {
        super(alias, titular, tipo);
        this.inversiones = new LinkedList<Inversion>();
    }

    public LinkedList<Inversion> getInversiones() {
        return inversiones;
    }

    public void invertirDinero(double monto, NivelRiesgo riesgo) {
        if (this.saldo >= monto) {
            this.saldo -= monto;
            
            Inversion nuevaInversion = new Inversion(monto, riesgo);
            this.inversiones.add(nuevaInversion);
            
            this.movimientos.add(new Movimiento(TipoMovimiento.INVERSION, -monto));
            
            JOptionPane.showMessageDialog(null, "Inversión creada. Saldo restante: $" + this.saldo);
        } else {
            JOptionPane.showMessageDialog(null, "No tenés fondos suficientes para invertir esa cantidad.");
        }
    }

    public void simularDias(int dias) {
        if (inversiones.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay inversiones activas.");
        }else {
            for (int i = 0; i < dias; i++) {
                for (Inversion inv : inversiones) {
                    inv.simularDia();
                }
            }
            JOptionPane.showMessageDialog(null, "Pasaron " + dias + " días. Inversiones actualizadas.");
		}
    }
    
    @Override
    public boolean retirar(double monto) {
        if (this.saldo >= monto) {
            this.saldo -= monto;
            this.movimientos.add(new Movimiento(TipoMovimiento.RETIRO, monto));
            return true;
        }
        JOptionPane.showMessageDialog(null, "Saldo insuficiente (Tu dinero está invertido).");
        return false;
    }

}
