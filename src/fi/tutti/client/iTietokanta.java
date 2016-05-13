package fi.tutti.client;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;

public interface iTietokanta extends RemoteService {
	public boolean uusiHenk(String nimi);
	public boolean uusiLaite(String nimi);
	public boolean poistaHenk(String nimi);
	public boolean poistaLaite(String nimi);
	
	public boolean lisaaHuolto(String henk, String laite, Date pvm);
	public boolean kuittaaHuolto(int id);
	
	public Vector<Henkilo> haeHenk();
	//public Vector<Henkilo> haeHenk(String nimi);
	public Vector<Laite> haeLaite();
	//public Vector<Laite> haeLaite(String nimi);
	
	//Metodi 1 = henkilö suunnitelma
	//2 = henkilö historia
	//3 = laite suunnitelma
	//4 = laite historia
	public Vector<Huolto> haeHuollot(String nimi, int metodi);
	//Overload ilman parametreja hakemaan kaikki
	public Vector<Huolto> haeHuollot();
	
	public boolean muutaStatus(int indeksi, int uusiStatus);
}
