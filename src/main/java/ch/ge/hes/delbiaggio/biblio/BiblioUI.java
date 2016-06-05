package ch.ge.hes.delbiaggio.biblio;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;

import ch.ge.hes.delbiaggio.biblio.ecran.EcranAccueil;

import com.vaadin.ui.UI;

@SuppressWarnings("serial")
public class BiblioUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = BiblioUI.class)
	public static class Servlet extends VaadinServlet {
		
	}	

	@Override
	protected void init(VaadinRequest request) {
		EcranAccueil ecranAccueil = new EcranAccueil();
		this.setContent(ecranAccueil);
		
	}
}