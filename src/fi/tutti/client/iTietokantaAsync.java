package fi.tutti.client;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fi.tutti.shared.Henkilo;
import fi.tutti.shared.Huolto;
import fi.tutti.shared.Laite;

public interface iTietokantaAsync {

	void haeHenk(AsyncCallback<Vector<Henkilo>> callback);

	void haeHuollot(String nimi, int metodi, AsyncCallback<Vector<Huolto>> callback);

	void haeHuollot(AsyncCallback<Vector<Huolto>> callback);

	void haeLaite(AsyncCallback<Vector<Laite>> callback);

	void kuittaaHuolto(int id, AsyncCallback<Boolean> callback);

	void lisaaHuolto(String henk, String laite, Date pvm, AsyncCallback<Boolean> callback);

	void muutaStatus(int indeksi, int uusiStatus, AsyncCallback<Boolean> callback);

	void poistaHenk(String nimi, AsyncCallback<Boolean> callback);

	void poistaLaite(String nimi, AsyncCallback<Boolean> callback);

	void uusiHenk(String nimi, AsyncCallback<Boolean> callback);

	void uusiLaite(String nimi, AsyncCallback<Boolean> callback);

}
