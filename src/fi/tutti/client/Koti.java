package fi.tutti.client;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import fi.tutti.shared.Henkilo;
import fi.tutti.shared.Huolto;
import fi.tutti.shared.Laite;

//import fi.tutti.client.iTietokanta;


public class Koti implements iKoti, EntryPoint {		
	private final iTietokantaAsync kanta = GWT.create(iTietokanta.class);
	private GUI kayttoliittyma;
	private Date nyt;
	private boolean apuBool;
	private Vector<Huolto> apuHuolto;
	private Vector<Laite> apuLaite;
	private Vector<Henkilo> apuHenkilo;
	
	@Override
	public void onModuleLoad(){
		//kanta = GWT.create(iTietokanta.class); 
		kayttoliittyma = new GUI(this);
		nyt = new Date();
		apuBool = false;
		apuHuolto = new Vector<Huolto>();
		apuLaite = new Vector<Laite>();
		apuHenkilo = new Vector<Henkilo>();
		huoltoTarkistus();
	}

	
	//Metodi tarkistamaan ja mahdollisesti muuttamaan huoltojen status
	//sek‰ pamauttamaan h‰lytys ikkuna GUI:hin
	private void huoltoTarkistus(){
		nyt = new Date();
		kanta.haeHuollot(new AsyncCallback<Vector<Huolto>>() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
			}

			public void onSuccess(Vector<Huolto> result) {
				for(int i = 0; i < result.size(); i++){
					if(result.get(i).pvm == nyt &&
						result.get(i).status == 0){
						kanta.muutaStatus(result.get(i).id, 1, new AsyncCallback<Boolean>(){
							public void onFailure(Throwable caught){
								caught.printStackTrace();
							}
							public void onSuccess(Boolean result){
								//Eip‰ t‰ss‰ varmaan mit‰‰n jakseta tehd‰
							}
						});
						kayttoliittyma.halyta();
					}
				}
			}
		});
	};
	
	@Override
	public boolean uusiHenk(String nimi) {
		kanta.uusiHenk(nimi, new AsyncCallback<Boolean>(){
			public void onFailure(Throwable caught){
				caught.printStackTrace();
			}
			public void onSuccess(Boolean result){
				apuBool = result;
			}
		});
		return apuBool;
	}

	@Override
	public boolean uusiLaite(String nimi) {
		kanta.uusiLaite(nimi, new AsyncCallback<Boolean>(){
			public void onFailure(Throwable caught){
				caught.printStackTrace();
			}
			public void onSuccess(Boolean result){
				apuBool = result;
			}
		});
		return apuBool;
	}

	@Override
	public boolean poistaHenk(String nimi) {
		kanta.poistaHenk(nimi, new AsyncCallback<Boolean>(){
			public void onFailure(Throwable caught){
				caught.printStackTrace();
			}
			public void onSuccess(Boolean result){
				apuBool = result;
			}
		});
		return apuBool;
	}

	@Override
	public boolean poistaLaite(String nimi) {
		kanta.poistaLaite(nimi, new AsyncCallback<Boolean>(){
			public void onFailure(Throwable caught){
				caught.printStackTrace();
			}
			public void onSuccess(Boolean result){
				apuBool = result;
			}
		});
		return apuBool;
	}

	@Override
	public boolean lisaaHuolto(String henk, String laite, Date pvm) {
		kanta.lisaaHuolto(henk, laite, pvm, new AsyncCallback<Boolean>(){
			public void onFailure(Throwable caught){
				caught.printStackTrace();
			}
			public void onSuccess(Boolean result){
				apuBool = result;
			}
		});
		return apuBool;
	}

	@Override
	public boolean kuittaaHuolto(int id) {
		kanta.kuittaaHuolto(id, new AsyncCallback<Boolean>(){
			public void onFailure(Throwable caught){
				caught.printStackTrace();
			}
			public void onSuccess(Boolean result){
				apuBool = result;
			}
		});
		return apuBool;
	}

	@Override
	public Vector<Huolto> haeHuollot(String nimi, int metodi) {
		kanta.haeHuollot(nimi,  metodi, new AsyncCallback<Vector<Huolto>>(){
			public void onFailure(Throwable caught){
				caught.printStackTrace();
			}
			public void onSuccess(Vector<Huolto> result){
				apuHuolto = result;
			}
		});
		return apuHuolto;
	}

	@Override
	public Vector<Laite> haeLaitteet() {
		kanta.haeLaite(new AsyncCallback<Vector<Laite>>(){
			public void onFailure(Throwable caught){
				caught.printStackTrace();
			}
			public void onSuccess(Vector<Laite> result){
				apuLaite = result;
			}
		});
		return apuLaite;
	}

	@Override
	public Vector<Henkilo> haeHenkilot() {
		kanta.haeHenk(new AsyncCallback<Vector<Henkilo>>(){
			public void onFailure(Throwable caught){
				caught.printStackTrace();
			}
			public void onSuccess(Vector<Henkilo> result){
				apuHenkilo = result;
			}
		});
		return apuHenkilo;
	}

}
