package Patio;

import Soporte.ITask;
import Soporte.Action;


public class TaskPatio implements ITask {
	
	ManagerPatio manager = new ManagerPatio();
	
	@Override
	public void execute(Action pAction, boolean esControl) {
		if (pAction==Action.crecerCespedMucho) {
			if(esControl) {manager.crecerCespedM();}
			else {System.out.println("Solo Control puede hacer crecer cesped");}
		}
		
		if (pAction==Action.humedecerSuelo) {
			if(esControl) {manager.humedecerSuelo();}
		else {System.out.println("Solo Control puede humedecer el suelo");}
		}
		if (pAction==Action.ensuciarPatio) {
			if(esControl) {manager.ensuciarPatio();}
		else {System.out.println("Solo Control puede ensuciar el patio");}
		}

		if (pAction==Action.mandarPlagas) {
			if(esControl) {manager.mandarPlagas();}
		else {System.out.println("Solo Control puede mandar plagas");}
		}
		if (pAction==Action.checkPatio) {
			if(!esControl) {manager.checkPatio();}
			else {System.out.println("Solo Patio puede hacer un check");}
		}
		if (pAction==Action.actPatio) {
			if(!esControl) {manager.actPatio();}
			else {System.out.println("Solo Patio puede hacer un act");}
		}
		
	}
}
