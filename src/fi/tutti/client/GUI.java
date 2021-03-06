package fi.tutti.client;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;


public class GUI implements iGUI {
	private DialogBox varoitusRuutu;
	private Koti main_;
	private ListBox henkLista;
	private ListBox laiteLista;
	private CellTable<Huolto> nayttoRuutu;
	private Logger logger = Logger.getLogger("GUI");
	
	public GUI(Koti main){
		logger.log(Level.SEVERE, "Rakentaja");
		main_ = main;
		alusta();
	}
	
	
	//Vector<Henkilo>, Vector<Laite>	
	@Override
	public void alusta() {
		Vector<Huolto> huollot = new Vector<Huolto>();
		varoitusRuutu = new DialogBox();

		//M��ritell��n tarvittavat komponentit
		henkLista = new ListBox();
		laiteLista  = new ListBox();
		
		Button henkSuunn = new Button("Huoltosuunnitelma");
		Button henkHist = new Button("Huoltohistoria");
		
		Button henkUusi = new Button("Luo uusi");
		Button henkPoista = new Button("Poista");
		
		Button laiteSuunn = new Button("Huoltosuunnitelma");
		Button laiteHist = new Button("Huoltohistoria");
		
		Button laiteUusi = new Button("Luo uusi");
		Button laitePoista = new Button("Poista", new ClickHandler(){
			@Override
			public void onClick(ClickEvent event){
				main_.poistaLaite(laiteLista.getSelectedItemText());
				laiteLista.removeItem(laiteLista.getSelectedIndex());
			}
		});
		Button huoltoUusi = new Button("Laadi uusi huolto");
		Button huoltoKuitt = new Button("Kuittaa");
		//FlexTable nayttoRuutu = new FlexTable();
		
		nayttoRuutu = new CellTable<Huolto>();
		TextColumn<Huolto> idSarake =  new TextColumn<Huolto>(){
			@Override
			public String getValue(Huolto obj){
				return Integer.toString(obj.id);
			}
		};
		
		TextColumn<Huolto> henkSarake = new TextColumn<Huolto>(){
			@Override
			public String getValue(Huolto obj){
				return obj.henkilo.nimi;
			}
		};
		
		TextColumn<Huolto> laiteSarake = new TextColumn<Huolto>(){
			@Override
			public String getValue(Huolto obj){
				return obj.laite.nimi;
			}
		};
		
		TextColumn<Huolto> pvmSarake = new TextColumn<Huolto>(){
			@Override
			public String getValue(Huolto obj){
				return obj.pvm.toString();
			}
		};
		nayttoRuutu.addColumn(idSarake, "Id");
		nayttoRuutu.addColumn(henkSarake, "Henkilo");
		nayttoRuutu.addColumn(laiteSarake, "Laite");
		nayttoRuutu.addColumn(pvmSarake, "Deadline");
		
		henkSuunn.addClickHandler(new ClickHandler(){
			
			@Override
			public void onClick(ClickEvent event){
				//Mit� tehd��n kun painetaan nappia
				Vector<Huolto> huollot1 = main_.haeHuollot(henkLista.getSelectedItemText(), 1);
				nayttoRuutu.setRowCount(huollot1.size(), true);
				nayttoRuutu.setRowData(0, huollot1);
			}
		});
		
		henkHist.addClickHandler(new ClickHandler(){
			
			@Override
			public void onClick(ClickEvent event){
				//Mit� tehd��n kun painetaan nappia
				Vector<Huolto> huollot1 = main_.haeHuollot(henkLista.getSelectedItemText(), 2);
				nayttoRuutu.setRowCount(huollot1.size(), true);
				nayttoRuutu.setRowData(0, huollot1);
			}
		});
		
		laiteSuunn.addClickHandler(new ClickHandler(){
			
			@Override
			public void onClick(ClickEvent event){
				//Mit� tehd��n kun painetaan nappia
				Vector<Huolto> huollot1 = main_.haeHuollot(laiteLista.getSelectedItemText(), 3);
				nayttoRuutu.setRowCount(huollot1.size(), true);
				nayttoRuutu.setRowData(0, huollot1);
			}
		});
		
		laiteHist.addClickHandler(new ClickHandler(){
			
			@Override
			public void onClick(ClickEvent event){
				//Mit� tehd��n kun painetaan nappia
				Vector<Huolto> huollot1 = main_.haeHuollot(laiteLista.getSelectedItemText(), 4);
				nayttoRuutu.setRowCount(huollot1.size(), true);
				nayttoRuutu.setRowData(0, huollot1);
			}
		});
		
		nayttoRuutu.setRowCount(huollot.size(), true);
		nayttoRuutu.setRowData(0,huollot);
		
		
		//M��ritell��n tarvittavat paneelit
		HorizontalPanel tausta = new HorizontalPanel();
		VerticalPanel vasenPaneeli = new VerticalPanel();
		VerticalPanel keskiPaneeli = new VerticalPanel();
		VerticalPanel oikeaPaneeli = new VerticalPanel();
		HorizontalPanel alaPaneeli = new HorizontalPanel();
		
		
		vasenPaneeli.add(henkLista);
		vasenPaneeli.add(laiteLista);
		
		keskiPaneeli.add(henkSuunn);
		keskiPaneeli.add(henkHist);
		keskiPaneeli.add(henkUusi);
		keskiPaneeli.add(henkPoista);
		keskiPaneeli.add(laiteSuunn);
		keskiPaneeli.add(laiteHist);
		keskiPaneeli.add(laiteUusi);
		keskiPaneeli.add(laitePoista);
		
		alaPaneeli.add(huoltoUusi);
		alaPaneeli.add(huoltoKuitt);
		
		oikeaPaneeli.add(nayttoRuutu);
		oikeaPaneeli.add(alaPaneeli);
		
		vasenPaneeli.setHeight("200px");
		keskiPaneeli.setHeight("200px");
		oikeaPaneeli.setHeight("200px");
		oikeaPaneeli.setWidth("250px");
		
		//Luodaan popup ikkunat
		final DialogBox henkRuutu = new DialogBox();
		final DialogBox laiteRuutu = new DialogBox();
		final DialogBox huoltoRuutu = new DialogBox();
		
		//M��ritet��n uuden henkil�n lis�ys ruutu
		henkRuutu.hide();
		henkRuutu.setWidth("200px");
		henkRuutu.setHeight("200px");
		henkRuutu.setPopupPosition(500, 0);
		
		final TextBox nimiLaatikko = new TextBox();
		Button uusiHenkNappi = new Button("OK", new ClickHandler(){
			
			@Override
			public void onClick(ClickEvent event){
				if(main_.uusiHenk(nimiLaatikko.getValue())){
					
					Vector<Henkilo> vector = main_.haeHenkilot();
					henkLista.clear();
					for(int i = 0; i < vector.size(); i++){
						henkLista.addItem(vector.elementAt(i).nimi);
					}
				}
				henkRuutu.hide();
			}
		});
		VerticalPanel henkTausta = new VerticalPanel();
		nimiLaatikko.setText("Uuden henkilon nimi");
		
		henkTausta.add(nimiLaatikko);
		henkTausta.add(uusiHenkNappi);
		henkTausta.setBorderWidth(3);
		henkRuutu.setGlassEnabled(true);
		henkRuutu.add(henkTausta);
		
		//M��ritell��n uuden laitteen lis�ys ruutu
		laiteRuutu.hide();
		laiteRuutu.setWidth("200px");
		laiteRuutu.setHeight("200px");
		laiteRuutu.setPopupPosition(500, 0);
		
		final TextBox laiteLaatikko = new TextBox();
		Button uusiLaiteNappi = new Button("OK", new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event){
				if(main_.uusiLaite(laiteLaatikko.getValue())){
					//Jollei kutsuta p�ivit� funktiota niin tarvitaan uusi metodi, joka hakee vain laitteet
					//Vapaaehtoinen hifistely
					Vector<Laite> vector = main_.haeLaitteet();
					laiteLista.clear();
					for(int i = 0; i < vector.size(); i++){
						laiteLista.addItem(vector.elementAt(i).nimi);
					}
				}
				laiteRuutu.hide();
			}
		});
		laiteLaatikko.setText("Uuden laitteen nimi");
		VerticalPanel laiteTausta = new VerticalPanel();
		
		laiteTausta.add(laiteLaatikko);
		laiteTausta.add(uusiLaiteNappi);
		laiteTausta.setBorderWidth(3);
		laiteRuutu.setGlassEnabled(true);
		laiteRuutu.add(laiteTausta);
		
		//M��ritell��n uuden huollon lis�ys ruutu
		huoltoRuutu.hide();
		huoltoRuutu.setWidth("200px");
		huoltoRuutu.setHeight("200px");
		huoltoRuutu.setPopupPosition(500, 0);
		
		HorizontalPanel ylempi = new HorizontalPanel();
		HorizontalPanel alempi = new HorizontalPanel();
		VerticalPanel huoltoTausta = new VerticalPanel();
		final ListBox henkLista2 = new ListBox();
		final ListBox laiteLista2 = new ListBox();
		final DateBox uusiPvm = new DateBox();
		Button uusiHuoltoOk = new Button("OK");
		
		henkLista2.setVisibleItemCount(1);
		Vector<Henkilo> vector1 = main_.haeHenkilot();
		henkLista2.clear();
		for(int i = 0; i < vector1.size(); i++){
			//henkLista2.addItem(vector1.elementAt(i).nimi);
		} 
		//laiteLista2.setVisibleItemCount(1);
		Vector<Laite> vector2 = main_.haeLaitteet();
		laiteLista2.clear();
		for(int i = 0; i < vector2.size(); i++){
			laiteLista2.addItem(vector2.elementAt(i).nimi);
		}
		
		ylempi.add(henkLista2);
		ylempi.add(laiteLista2);
		alempi.add(uusiPvm);
		alempi.add(uusiHuoltoOk);
		huoltoTausta.add(ylempi);
		huoltoTausta.add(alempi);
		huoltoRuutu.add(huoltoTausta);
		huoltoRuutu.setGlassEnabled(true);
		
		
		//M��ritell��n varoitusruutu
		varoitusRuutu.hide();
		varoitusRuutu.setWidth("200px");
		varoitusRuutu.setHeight("200px");
		varoitusRuutu.setPopupPosition(500, 0);
		VerticalPanel varoitusTausta = new VerticalPanel();
		HorizontalPanel ylempi2 = new HorizontalPanel();
		Label varTeksti = new Label();
		varTeksti.setText("Jokin huolto on myohassa. Tarkista suunnitelma!");
		varoitusRuutu.setText("Varoitus");
		varoitusRuutu.add(varTeksti);
		
		Button varoitusOK = new Button("OK");
		varoitusOK.addClickHandler(new ClickHandler(){
			
			@Override
			public void onClick(ClickEvent event){
				//Mit� tehd��n kun painetaan nappia
				varoitusRuutu.hide();
			}
		});
		
		ylempi2.add(varoitusOK);
		varoitusTausta.add(ylempi2);
		varoitusRuutu.add(varoitusTausta);
		varoitusRuutu.setGlassEnabled(true);
		
		//M��ritell��n henkil�iden listaus
		//T�m� tulee automatisoida kunhan p��st��n varsinaiseen toiminnallisuuteen
		vector1 = main_.haeHenkilot();
		henkLista.clear();
		for(int i = 0; i < vector1.size(); i++){
			henkLista.addItem(vector1.elementAt(i).nimi);
		}
		
		vector2 = main_.haeLaitteet();
		laiteLista.clear();
		for(int i = 0; i < vector2.size(); i++){
			laiteLista.addItem(vector2.elementAt(i).nimi);
		}
		
		//M��ritell��n monta elementti� on n�kyvill�
		henkLista.setVisibleItemCount(4);
		laiteLista.setVisibleItemCount(4);
		
		tausta.add(vasenPaneeli);
		tausta.add(keskiPaneeli);
		tausta.add(oikeaPaneeli);

		RootPanel.get().add(tausta);
		
		henkPoista.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event){
				main_.poistaHenk(henkLista.getSelectedItemText());
				henkLista.removeItem(henkLista.getSelectedIndex());	
			}
		});
		
		/*
		laitePoista.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event){
				main_.poistaLaite(laiteLista.getSelectedItemText());
				laiteLista.removeItem(laiteLista.getSelectedIndex());
			}
		});
		*/
		
		//Tapahtumak�sittelij� avaamaan uuden henkil�n luomiseen tarkoitettu ruutu
		henkUusi.addClickHandler(new ClickHandler(){
	
			@Override
			public void onClick(ClickEvent event){
				//Mit� tehd��n kun painetaan nappia
				henkRuutu.show();
			}
		});
		//Ja sulkemaan se
		/*uusiHenkNappi.addClickHandler(new ClickHandler(){
			
			@Override
			public void onClick(ClickEvent event){
				if(main_.uusiHenk(nimiLaatikko.getValue())){
					Vector<String> vector = main_.haeHenkilot();
					henkLista2.clear();
					for(int i = 0; i < vector.size(); i++){
						henkLista2.addItem(vector.elementAt(i));
					}
				}
				henkRuutu.hide();
			}
		});
		*/
		//Laitteen lis��mis ruudun avaaminen
		laiteUusi.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event){
				laiteRuutu.show();
			}
		});
		//Ja suljetaan
		/*
		uusiLaiteNappi.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event){
				if(main_.uusiLaite(laiteLaatikko.getValue())){
					//Jollei kutsuta p�ivit� funktiota niin tarvitaan uusi metodi, joka hakee vain laitteet
					Vector<String> vector = main_.haeLaitteet();
					laiteLista2.clear();
					for(int i = 0; i < vector.size(); i++){
						laiteLista2.addItem(vector.elementAt(i));
					}
				}
				laiteRuutu.hide();
			}
		});
		*/
		
		//Huollon lis�ys ruutu
		huoltoUusi.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event){
				huoltoRuutu.show();
			}
		});
		//Ja sulkeminen
		uusiHuoltoOk.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event){
				if(main_.lisaaHuolto(henkLista.getSelectedItemText(), laiteLista.getSelectedItemText(), uusiPvm.getValue())){
					
				}
				huoltoRuutu.hide();
			}
		});
		
	}

	@Override
	public void paivita(Vector<Henkilo> henkilot, Vector<Laite> laitteet) {
		henkLista.clear();
		laiteLista.clear();
		for(int i = 0; i < henkilot.size(); i++){
			henkLista.addItem(henkilot.elementAt(i).nimi);
		}
		for(int i = 0; i < laitteet.size(); i++){
			laiteLista.addItem(laitteet.elementAt(i).nimi);
		}
		
	}

	@Override
	public void halyta() {
		varoitusRuutu.show();	
	}

}
