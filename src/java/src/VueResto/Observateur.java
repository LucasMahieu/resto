package VueResto;
import ControleurResto.*;
import java.util.*;

public abstract class Observateur implements Observer{
	/**
	 * This method is called whenever the observed object is changed. 
	 * An application calls an Observable object's notifyObservers method to have all the object's observers notified of the change.
	 * @param o the observable object
	 * @param arg an argument passer to the notifyObservers method
	 */
	public abstract void update(Observable o, Object arg);
}
