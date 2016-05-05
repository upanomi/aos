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
		kayttoliittyma = new GUI(this);
		kanta = new Tietokanta();
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Vector<Huolto> haeHuollot(String nimi, int metodi) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Laite> haeLaitteet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Henkilo> haeHenkilot() {
		// TODO Auto-generated method stub
		return null;
	}

}
