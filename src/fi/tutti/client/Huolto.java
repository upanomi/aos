package fi.tutti.client;

import java.util.Date;

public class Huolto {
	public Henkilo henkilo;
	public Laite laite;
	public Date pvm;
	public int id;
	public int status;
	
	public Huolto(){
		henkilo = new Henkilo();
		laite = new Laite();
		id = -1;
		pvm = new Date(0);
	}
	
	public Huolto(Henkilo henk, Laite lait, Date paiva, int indeksi){
		this.henkilo = henk;
		this.laite = lait;
		this.pvm = paiva;
		this.id = indeksi;
	}
}
