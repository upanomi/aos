package fi.tutti.client;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.core.client.EntryPoint;



public class Koti implements EntryPoint, iKoti {		
	private Tietokanta kanta;
	private GUI kayttoliittyma;
	private Date nyt;
	
	@Override
	public void onModuleLoad(){
		kanta = new Tietokanta();
		//kanta.alusta();
		
		kayttoliittyma = new GUI(this);
		//huoltoTarkistus();
		}

	
	//Metodi tarkistamaan ja mahdollisesti muuttamaan huoltojen status
	//sek� pamauttamaan h�lytys ikkuna GUI:hin
	private void huoltoTarkistus(){
		nyt = new Date();
		Vector<Huolto> huollot = kanta.haeHuollot();
		for(int i = 0; i < huollot.size(); i++){
			if(huollot.get(i).pvm == nyt &&
				huollot.get(i).status == 0){
				kanta.muutaStatus(huollot.get(i).id,1);
				kayttoliittyma.halyta();
			}
		}
	}
	
	@Override
	public boolean uusiHenk(String nimi) {
		return kanta.uusiHenk(nimi);
	}

	@Override
	public boolean uusiLaite(String nimi) {
		return kanta.uusiLaite(nimi);
	}

	@Override
	public boolean poistaHenk(String nimi) {
		return kanta.poistaHenk(nimi);
	}

	@Override
	public boolean poistaLaite(String nimi) {
		return kanta.poistaLaite(nimi);
	}

	@Override
	public boolean lisaaHuolto(String henk, String laite, Date pvm) {
		return kanta.lisaaHuolto(henk, laite, pvm);
	}

	@Override
	public boolean kuittaaHuolto(int id) {
		return kanta.kuittaaHuolto(id);
	}

	@Override
	public Vector<Huolto> haeHuollot(String nimi, int metodi) {
		return kanta.haeHuollot(nimi, metodi);
	}

	@Override
	public Vector<Laite> haeLaitteet() {
		return kanta.haeLaite();
	}

	@Override
	public Vector<Henkilo> haeHenkilot() {
		return kanta.haeHenk();
	}

}
