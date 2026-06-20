package Observador;



public interface Observable {	
	public void addOserver(PintorObserver observable);
	public void notifyObserver();
}
