package ch.ge.hes.delbiaggio.biblio.composant;

import com.vaadin.server.VaadinService;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

import ch.ge.hes.delbiaggio.biblio.BiblioUI;
import ch.ge.hes.delbiaggio.biblio.listener.menuBar.ConnectionPress;
import ch.ge.hes.delbiaggio.biblio.listener.menuBar.DeconnexionPressed;
import ch.ge.hes.delbiaggio.biblio.listener.menuBar.LivresPressed;

public class ComposantBarMenuWthConnexion extends HorizontalLayout{
	private Label lbNampeApp; 
	private Button btnLivres; 
	
	public ComposantBarMenuWthConnexion() {
		initBarMenu();
		addComponents(lbNampeApp,btnLivres);
		setComponentAlignment(btnLivres, Alignment.TOP_LEFT);
	}
		
	private void initBarMenu(){
		setMargin(true);
		setWidth("100%");
		lbNampeApp = new Label("App Bibliothèque");
		lbNampeApp.setWidth("100%");
		btnLivres = new Button("Livres");
		btnLivres.setStyleName("link");
		btnLivres.setWidth("100%");
		btnLivres.addClickListener(new LivresPressed());		
	}
}
