package fi.tutti.client;

import java.util.Date;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Vector;

import com.google.gwt.core.client.EntryPoint;


public class Koti implements iKoti, EntryPoint {		
	GUI kayttoliittyma;
	Tietokanta kanta;
	
	@Override
	public void onModuleLoad(){
		kanta = new Tietokanta();
		kayttoliittyma = new GUI(this);
		
		huoltoTarkistus();
	}

	
	//Metodi tarkistamaan ja mahdollisesti muuttamaan huoltojen status
	//sekä pamauttamaan hälytys ikkuna GUI:hin
	private void huoltoTarkistus(){
		Date nyt = new Date();
		Vector<Huolto> huollot = kanta.haeHuollot();
		
		for(int i = 0; i < huollot.size(); i++){
			if(huollot.get(i).pvm == nyt &&
				huollot.get(i).status == 0){
				kanta.muutaStatus(huollot.get(i).id, 1);
				kayttoliittyma.paivita(kanta.haeHenk(), kanta.haeLaite());
				kayttoliittyma.halyta();
			}
		}
	};
	
	@Override
	public boolean uusiHenk(String nimi) {
		boolean tulos = kanta.uusiHenk(nimi);
		kayttoliittyma.paivita(kanta.haeHenk(), kanta.haeLaite());
		return tulos;
	}

	@Override
	public boolean uusiLaite(String nimi) {
		boolean tulos = kanta.uusiLaite(nimi);
		kayttoliittyma.paivita(kanta.haeHenk(), kanta.haeLaite());
		return tulos;
	}

	@Override
	public boolean poistaHenk(String nimi) {
		boolean tulos = kanta.poistaHenk(nimi);
		kayttoliittyma.paivita(kanta.haeHenk(), kanta.haeLaite());
		return tulos;
	}

	@Override
	public boolean poistaLaite(String nimi) {
		boolean tulos = kanta.poistaLaite(nimi);
		kayttoliittyma.paivita(kanta.haeHenk(), kanta.haeLaite());
		return tulos;
	}

	@Override
	public boolean lisaaHuolto(String henk, String laite, Date pvm) {
		boolean tulos = kanta.lisaaHuolto(henk, laite, pvm);
		kayttoliittyma.paivita(kanta.haeHenk(), kanta.haeLaite());
		return tulos;
	}

	@Override
	public boolean kuittaaHuolto(int id) {
		boolean tulos = kanta.kuittaaHuolto(id);
		kayttoliittyma.paivita(kanta.haeHenk(), kanta.haeLaite());
		return tulos;
	}

	@Override
	public Vector<Huolto> haeHuollot(String nimi, int metodi) {
		return kanta.haeHuollot(nimi,  metodi);
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
