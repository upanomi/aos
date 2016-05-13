package fi.tutti.client;

import java.util.Date;
import java.util.Vector;

import fi.tutti.shared.Henkilo;
import fi.tutti.shared.Huolto;
import fi.tutti.shared.Laite;

public interface iKoti {
	public boolean uusiHenk(String nimi);
	public boolean uusiLaite(String nimi);
	public boolean poistaHenk(String nimi);
	public boolean poistaLaite(String nimi);
	
	public boolean lisaaHuolto(String henk, String laite, Date pvm);
	public boolean kuittaaHuolto(int id);
	
	//Metodi 1 = henkilö suunnitelma
	//2 = henkilö historia
	//3 = laite suunnitelma
	//4 = laite historia
	public Vector<Huolto> haeHuollot(String nimi, int metodi);
	public Vector<Laite> haeLaitteet();
	public Vector<Henkilo> haeHenkilot();
}
