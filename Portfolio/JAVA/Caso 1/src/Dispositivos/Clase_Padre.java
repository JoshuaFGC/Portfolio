package Dispositivos;

public class Clase_Padre {
	
	boolean Electricidad = true;
	boolean Internet = true;

	public void recuperarLuz(){
		System.out.println("Activando generadores de emergencia."+"\nTodos los dispositivos de la casa vuelven a tener electricidad");
		setElectricidad(true);
		System.out.println("Recuerde reiniciar el modem para que vuelva a su correcto funcionamiento.");
	}

	public boolean isElectricidad() {
		return Electricidad;
	}

	public void setElectricidad(boolean electricidad) {
		Electricidad = electricidad;
		if(!electricidad){
			setInternet(false);
			System.out.println("Se ha ido la electricidad y el internet.");
			recuperarLuz();
		}
	}

	public boolean isInternet() {
		return Internet;
	}

	public void setInternet(boolean internet) {
		Internet = internet;
	}
	
	
}

class Lavadora extends Clase_Padre{

	public String Lavar(){
		String texto ="";

		if(isElectricidad()){
			texto="Se ha iniciado el proceso de lavado.";

		}else{
			texto="Lavadora apagada";
		}

		return texto;
	}

}

class Modem extends Clase_Padre{

	public void resetearModem(){

		System.out.println("El modem se encuentra desconfigurado por la perdida de electricidad."+
		"\nReiniciando modem..."+
		"\nSistema reiniciado.");
	}
}