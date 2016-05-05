package fi.tutti.client;

import java.util.Date;
//import java.sql.DriverManager;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;

//import com.gargoylesoftware.htmlunit.Version;

import java.util.Vector;


//import com.google.appengine.api.utils.SystemProperty;
//import com.google.cloud.sql.jdbc.*;
//import com.mysql.jdbc.*;
/*
import com.google.appengine.api.utils.SystemProperty;
import com.google.appengine.api.rdbms.AppEngineDriver;
import com.google.cloud.sql.jdbc.Connection;
import com.google.cloud.sql.jdbc.ResultSet;
import com.google.cloud.sql.jdbc.Driver;
*/

public class Tietokanta  implements iTietokanta {
	
	/*static Connection conn = null;
	static Statement stmt = null;
	static ResultSet rs = null;*/
	
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
		
		
		
		//Yhdistäminen tietokantaan	
		
		//String url = "jdbc:google:mysql://aoslaitehuoltohallinta:laitehuoltokanta";
		//String user = "root";
		//String password = "aosmysql";
		/*
		try {
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
		}
		catch (SQLException ex){
			//Logger lgr = Logger.getLogger(Version.class.getName());
			//lgr.log(Level.SEVERE, ex.getMessage(), ex);
			ex.printStackTrace();
		}
		*/
		/*
		String url = null;
		if (SystemProperty.environment.value() ==
		    SystemProperty.Environment.Value.Production) {
			  // Connecting from App Engine.
			  // Load the class that provides the "jdbc:google:mysql://"
			  // prefix.
				try {
					Class.forName("com.mysql.jdbc.GoogleDriver");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				url =
		    "jdbc:google:mysql://aoslaitehuoltohallinta:laitehuoltokanta?user=root";
		} else {
			// Connecting from an external network.
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			url = "jdbc:mysql://207.223.162.30:3306?user=root";
		}
		

		//Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		/*
		try {
			ResultSet rs = conn.createStatement().executeQuery(
			    "SELECT 1 + 1");
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		
	}
	
	@Override
	public boolean uusiHenk(String nimi) {
		boolean success = true;
		//String sqlquery = "INSERT INTO henkilot " + nimi;
		/*
 		try {
			conn.createStatement().executeQuery(sqlquery);
			return true;
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		}
		*//*
		try {
			rs = stmt.executeQuery(sqlquery);
		}
		catch (SQLException ex){
			success = false;
			//Logger lgr = Logger.getLogger(Version.class.getName());
			//lgr.log(Level.SEVERE, ex.getMessage(), ex);
			ex.printStackTrace();
		}*/
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

}
