package finalPOO;

import java.util.LinkedList;

public class Inversion {
	
	protected double montoInicial;
    protected double montoActual;
    protected NivelRiesgo riesgo;
    protected LinkedList<String> historialDiario;

    public Inversion(double monto, NivelRiesgo riesgo) {
        this.montoInicial = monto;
        this.montoActual = monto;
        this.riesgo = riesgo;
        this.historialDiario = new LinkedList<String>();
        this.historialDiario.add("Monto de Inicio: $" + monto + " - Riesgo: " + riesgo);
    }

    public double getMontoActual() {
        return montoActual;
    }
    
    public double getMontoInicial() {
		return montoInicial;
	}

	public void setMontoInicial(double montoInicial) {
		this.montoInicial = montoInicial;
	}

	public NivelRiesgo getRiesgo() {
		return riesgo;
	}

	public void setRiesgo(NivelRiesgo riesgo) {
		this.riesgo = riesgo;
	}

	public void setMontoActual(double montoActual) {
		this.montoActual = montoActual;
	}

	public void setHistorialDiario(LinkedList<String> historialDiario) {
		this.historialDiario = historialDiario;
	}
	
	public LinkedList<String> getHistorialDiario() {
        return historialDiario;
    }
	
    @Override
    public String toString() {
        return "Inv. " + riesgo + " - Inicio: $" + montoInicial + " - Actual: $" + montoActual;
    }

    public void simularDia() {
        double tasa = 0;
        String estadoMercado;

        switch (riesgo) {
            case BAJO: 
                tasa = (Math.random() * 0.04) - 0.01; // -1% a 3%
                break;
            case MEDIO: 
                tasa = (Math.random() * 0.09) - 0.03; // -3% a 6%
                break;
            case ALTO: 
                tasa = (Math.random() * 0.25) - 0.10; // -10% a 15%
                break;
        }

        double rendimiento = this.montoActual * tasa;
        this.montoActual += rendimiento;
        
        if (tasa > 0) {
            estadoMercado = "ALCISTA";
        } else if (tasa < 0) {
            estadoMercado = "BAJISTA";
        } else {
            estadoMercado = "ESTABLE";
        }

        String reporte = "El mercado estuvo: " + estadoMercado + 
                         " (" + (tasa * 100) + "%) | Generado: $" + rendimiento + 
                         " | Saldo: $" + montoActual;
        
        this.historialDiario.add(reporte);
    }
}
