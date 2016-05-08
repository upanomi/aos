package fi.tutti.client;

import java.util.Date;
import java.util.Vector;

public class Tietokanta  implements iTietokanta {
	
	private Vector<Huolto> huollot;
	private Vector<Laite> laitteet;
	private Vector<Henkilo> henkilot;
	private int maxId;
	
	public Tietokanta(){
		//Tietovaraston luominen
		huollot = new Vector<Huolto>();
		laitteet = new Vector<Laite>();
		henkilot = new Vector<Henkilo>();
		maxId = 0;
		
		
	}
	
	@Override
	public boolean uusiHenk(String nimi) {
		boolean success = true;
		
		for(int i = 0; i < henkilot.size(); i++){
			if(henkilot.get(i).nimi == nimi){
				return false;
			}
		}
		
		Henkilo uusi = new Henkilo(nimi);
		henkilot.add(uusi);
		
		return success;
	}

	@Override
	public boolean uusiLaite(String nimi) {
		for(int i = 0; i < laitteet.size(); i++){
			if(laitteet.get(i).nimi == nimi){
				return false;
			}
		}
		
		Laite uusi = new Laite(nimi);
		laitteet.add(uusi);
		
		return true;
	}

	@Override
	public boolean poistaHenk(String nimi) {
		
		for(int i = 0; i < henkilot.size(); i++){
			if(henkilot.get(i).nimi == nimi){
				henkilot.remove(i);
				return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean poistaLaite(String nimi) {
		for(int i = 0; i < laitteet.size(); i++){
			if(laitteet.get(i).nimi == nimi){
				laitteet.remove(i);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean lisaaHuolto(String henk, String laite, Date pvm) {
		Henkilo uusi_henk = new Henkilo(henk);
		Laite uusi_laite = new Laite(laite);
		Huolto uusi_huolto = new Huolto(uusi_henk, uusi_laite, pvm, maxId + 1);
		++maxId;
		huollot.add(uusi_huolto);
		
		return true;
	}

	@Override
	public boolean kuittaaHuolto(int indeksi) {
		
		for(int i = 0; i < huollot.size(); i++){
			if(huollot.get(i).id == indeksi){
				
				if(huollot.get(i).status == 0){
					huollot.get(i).status = 3;
					return true;
				}
				else if(huollot.get(i).status == 1){
					huollot.get(i).status = 2;
					return true;
				}
				else {
					return false;
				}
				
			}
		}
		
		return false;
	}

	@Override
	public Vector<Henkilo> haeHenk() {
		return henkilot;
	}

	/*@Override
	public Vector<String> haeHenk(String nimi) {
		
		return null;
	}*/

	@Override
	public Vector<Laite> haeLaite() {
		return laitteet;
	}

	/*
	@Override
	public Vector<String> haeLaite(String nimi) {
		
		return null;
	}*/

	@Override
	public Vector<Huolto> haeHuollot(String nimi, int metodi) {
		Vector<Huolto> tulos = new Vector<Huolto>();
		
		//Jos haetaan henkilon suunnitelma
		if(metodi == 1){
			for(int i = 0; i < huollot.size(); i++){
				
				if((huollot.get(i).status == 1 || huollot.get(i).status == 0) &&
					huollot.get(i).henkilo.nimi == nimi){
					tulos.add(huollot.get(i));
				}
			}
			
			return tulos;
		} 
		//Henkilön historia
		else if(metodi == 2){
			for(int i = 0; i < huollot.size(); i++){
				
				if((huollot.get(i).status == 2 || huollot.get(i).status == 3) &&
					huollot.get(i).henkilo.nimi == nimi){
					tulos.add(huollot.get(i));
				}
			}
			
			return tulos;
		}
		//Laitteen suunnitelma
		else if(metodi == 3){
			for(int i = 0; i < huollot.size(); i++){
				
				if((huollot.get(i).status == 1 || huollot.get(i).status == 0) &&
					huollot.get(i).laite.nimi == nimi){
					tulos.add(huollot.get(i));
				}
			}
			
			return tulos;
		}
		//Laitteen historia
		else if(metodi == 4){
			for(int i = 0; i < huollot.size(); i++){
				
				if((huollot.get(i).status == 2 || huollot.get(i).status == 3) &&
					huollot.get(i).laite.nimi == nimi){
					tulos.add(huollot.get(i));
				}
			}
			
			return tulos;
		}
		
		//Jos mikään ei täsämää, palautetaan null
		return null;
	}

	@Override
	public boolean muutaStatus(int indeksi, int uusiStatus) {
		huollot.get(indeksi).status = uusiStatus;
		return true;
	}

	@Override
	public Vector<Huolto> haeHuollot(){
		return huollot;
	}
}
