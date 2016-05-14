package fi.tutti.client;

import java.util.Vector;

public interface iGUI {
	public void alusta();
	
	public void paivita(Vector<Henkilo> henkilot, Vector<Laite> laitteet);
	public void halyta();

}
