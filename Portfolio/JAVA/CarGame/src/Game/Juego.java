package Game;


import Jugador.carro;
import Jugador.Perfil;
import Pista.track;



public class Juego {
	private track pistaActual;
	private carro carroJ;
	private Rivales rival;
	

	
	public void selecCarroPista(carro pCarroSelec, track pTrackSelec) {
		this.pistaActual = pTrackSelec;
		this.carroJ = pCarroSelec;
		System.out.println("Pista: "+pistaActual.getLocation()+"Carro: "+carroJ);
	}
	
	public void selecRival(Rivales pCarroR) {
		this.rival = pCarroR;
		System.out.println("Su rival es: "+rival);
		
	}
	
	public void startCarrera() {
		System.out.println("Inicio");
		pistaActual.start();
	}
	
	public void evPosicionJ() {
		System.out.println("Evaluando posicion del jugador...");
	}
	
	public void fin() {
		System.out.println("El jugador ha llegado a la meta");
	}
	
	
	public static void main(String args[]) {
		System.out.println("Hot Wheels TR 0.5");
		
		carro Slideout = new carro("Slideout");
		carro Rash1 = new carro("Rash 1");
		carro SolAireCX4 = new carro("Sol Aire CX-4");
		carro HWCar = new carro("Hot Wheels Car");
		
		rival TowJam = new rival("Tow Jam");
		rival RockBuster = new rival("Rock Buster");
		rival TwinMill = new rival("Twin Mill");
		
		track SludgeWorks = new track("SludgeWorks");
		track ColdFusion = new track("ColdFusion");
		
		Juego user = new Juego();
		user.selecCarroPista(SolAireCX4, ColdFusion);
		SolAireCX4.asigPista(ColdFusion);
		user.startCarrera();
		
		Perfil jugador = new Perfil("Joshua", SolAireCX4);
		SolAireCX4.acelerar();
		
		user.evPosicionJ();
		
		SolAireCX4.der();
		
		user.evPosicionJ();
		
		user.fin();
		
	}
}
