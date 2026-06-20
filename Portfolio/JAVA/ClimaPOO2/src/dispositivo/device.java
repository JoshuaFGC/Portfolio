package dispositivo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class device implements Runnable{
	
	protected String nombre;
	protected double min;
	protected double max;
	protected double probabilidad;
	protected double porcentaje;
	protected boolean pCalibracion; //Para la validacion de una nueva calibracion
	protected List<Double> datosGenerados = new ArrayList<>();
	

	
	public void calibrar(double pMin, double pMax) {
		min = pMin;
		max = pMax;

		
	}
	
	
	

	

	
	public void descalibrar(){
		Random random2 = new Random();
		double factorDescalibracion = 1 + (double)(random2 .nextDouble() * porcentaje * 2 - porcentaje);
		min -= min * factorDescalibracion;
		max += max * factorDescalibracion;
	}
	
	public List<Double> getData(){
		
		return datosGenerados;
	}

	@Override
	public void run() {
		Random random = new Random();
        while (true) {
            if (random.nextDouble() < probabilidad) {
                descalibrar();
            }

            double dato = min + random.nextFloat() * (max - min);
            
            datosGenerados.add(dato);
            System.out.println(dato);
            
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


