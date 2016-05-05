package fi.tutti.client;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;


public class GUI implements iGUI {
	
	private Koti main_;
	
	public GUI(Koti main){
		main_ = main;
		alusta();
	}
	
	
	//Vector<Henkilo>, Vector<Laite>	
	@Override
	public void alusta() {
		List<Huolto> huollot = Arrays.asList(
				new Huolto(),
				new Huolto());

		//M‰‰ritell‰‰n tarvittavat komponentit
		final ListBox henkLista = new ListBox();
		final ListBox laiteLista = new ListBox();
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
		
		CellTable<Huolto> nayttoRuutu = new CellTable<Huolto>();
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
		nayttoRuutu.addColumn(henkSarake, "Henkilo");
		nayttoRuutu.addColumn(laiteSarake, "Laite");
		nayttoRuutu.addColumn(pvmSarake, "Deadline");
		
		nayttoRuutu.setRowCount(huollot.size(), true);
		nayttoRuutu.setRowData(0,huollot);
		
		
		//M‰‰ritell‰‰n tarvittavat paneelit
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
		final DialogBox varoitusRuutu = new DialogBox();
		
		//M‰‰ritet‰‰n uuden henkilˆn lis‰ys ruutu
		henkRuutu.hide();
		henkRuutu.setWidth("200px");
		henkRuutu.setHeight("200px");
		henkRuutu.setPopupPosition(500, 0);
		
		final TextBox nimiLaatikko = new TextBox();
		Button uusiHenkNappi = new Button("OK", new ClickHandler(){
			
			@Override
			public void onClick(ClickEvent event){
				if(main_.uusiHenk(nimiLaatikko.getValue())){
					//Vapaaehtoinen hifistely
					/*
					Vector<String> vector = main_.haeHenkilot();
					henkLista.clear();
					for(int i = 0; i < vector.size(); i++){
						henkLista.addItem(vector.elementAt(i));
					}
					*/
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
		
		//M‰‰ritell‰‰n uuden laitteen lis‰ys ruutu
		laiteRuutu.hide();
		laiteRuutu.setWidth("200px");
		laiteRuutu.setHeight("200px");
		laiteRuutu.setPopupPosition(500, 0);
		
		final TextBox laiteLaatikko = new TextBox();
		Button uusiLaiteNappi = new Button("OK", new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event){
				if(main_.uusiLaite(laiteLaatikko.getValue())){
					//Jollei kutsuta p‰ivit‰ funktiota niin tarvitaan uusi metodi, joka hakee vain laitteet
					//Vapaaehtoinen hifistely
					/*
					Vector<String> vector = main_.haeLaitteet();
					laiteLista.clear();
					for(int i = 0; i < vector.size(); i++){
						laiteLista.addItem(vector.elementAt(i));
					}
					*/
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
		
		//M‰‰ritell‰‰n uuden huollon lis‰ys ruutu
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
		henkLista2.addItem("Henkilo A");
		henkLista2.addItem("Henkilo B");
		henkLista2.addItem("Henkilo C");
		henkLista2.addItem("Henkilo D");
		laiteLista2.setVisibleItemCount(1);
		laiteLista2.addItem("Laite A");
		laiteLista2.addItem("Laite B");
		laiteLista2.addItem("Laite C");
		laiteLista2.addItem("Laite D");
		
		ylempi.add(henkLista2);
		ylempi.add(laiteLista2);
		alempi.add(uusiPvm);
		alempi.add(uusiHuoltoOk);
		huoltoTausta.add(ylempi);
		huoltoTausta.add(alempi);
		huoltoRuutu.add(huoltoTausta);
		huoltoRuutu.setGlassEnabled(true);
		
		/*
		//M‰‰ritell‰‰n varoitusruutu
		Label varTeksti = new Label();
		varTeksti.setText("Jokin huolto on myohassa. Tarkista suunnitelma!");
		
		varoitusRuutu.setText("Varoitus");
		varoitusRuutu.add(varTeksti);
		*/
		//M‰‰ritell‰‰n henkilˆiden listaus
		//T‰m‰ tulee automatisoida kunhan p‰‰st‰‰n varsinaiseen toiminnallisuuteen
		henkLista.addItem("Henkilo A");
		henkLista.addItem("Henkilo B");
		henkLista.addItem("Henkilo C");
		henkLista.addItem("Henkilo D");
		
		laiteLista.addItem("Laite A");
		laiteLista.addItem("Laite B");
		laiteLista.addItem("Laite C");
		laiteLista.addItem("Laite D");
		
		//M‰‰ritell‰‰n monta elementti‰ on n‰kyvill‰
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
		
		//Tapahtumak‰sittelij‰ avaamaan uuden henkilˆn luomiseen tarkoitettu ruutu
		henkUusi.addClickHandler(new ClickHandler(){
	
			@Override
			public void onClick(ClickEvent event){
				//Mit‰ tehd‰‰n kun painetaan nappia
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
		//Laitteen lis‰‰mis ruudun avaaminen
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
					//Jollei kutsuta p‰ivit‰ funktiota niin tarvitaan uusi metodi, joka hakee vain laitteet
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
		
		//Huollon lis‰ys ruutu
		huoltoUusi.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event){
				huoltoRuutu.show();
			}
		});
		//Ja sulkeminen
		uusiHuoltoOk.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event){
				if(main_.lisaaHuolto(henkLista2.getSelectedItemText(), laiteLista2.getSelectedItemText(), uusiPvm.getValue())){
						//Jotain t‰h‰n?
					}
				huoltoRuutu.hide();
			}
		});
		
	}

	@Override
	public void paivita(Vector<Henkilo> henkilot, Vector<Laite> laitteet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void halyta() {
		// TODO Auto-generated method stub
		
	}

}
