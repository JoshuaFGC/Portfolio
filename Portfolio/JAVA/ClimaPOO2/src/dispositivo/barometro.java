package dispositivo;

import java.util.List;

public class barometro extends device{

	
	public barometro(String pNombre, double pMin, double pMax, double pProbabilidad, double pPercDescalibre) {
		nombre = pNombre;
		min = pMin;
		max = pMax;
		probabilidad = pProbabilidad;
		porcentaje = pPercDescalibre;
	}
	
	public void calibrarBaro(double pMin, double pMax) {
		super.calibrar(pMin, pMax);
	}
	
	public void descalibrar(){
		super.descalibrar();
	}
	
	public List<Double> getDataBaro(){
		datosGenerados = super.getData();
		return datosGenerados;
	}
	
	public double getMin() {
		return min;
	}

	public double getMax() {
		
		return max;
	}
}
