package Pista;



public class track {
	
	private String location;
	
	
	
	public track(String pLocation) {
		this.location = pLocation;
	}
	
	public String getLocation() {
		return this.location;
	}
	
	public void start() {
		System.out.println("Iniciando la pista: "+this.location);
	}


		
	}

}
