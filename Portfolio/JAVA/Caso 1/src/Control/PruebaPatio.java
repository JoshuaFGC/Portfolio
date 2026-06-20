package Control;

import Patio.TaskPatio;
import Soporte.Action;

public class PruebaPatio extends Prueba{
	
    public

        void hacerPrueba(String pTask){
        	Main.tareasPatio.execute(Action.valueOf(pTask), true);
        }
}
