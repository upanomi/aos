package fi.tutti.client;

import java.util.Vector;

import fi.tutti.shared.Henkilo;
import fi.tutti.shared.Laite;

public interface iGUI {
	public void alusta();
	
	public void paivita(Vector<Henkilo> henkilot, Vector<Laite> laitteet);
	public void halyta();

}
