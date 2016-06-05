package ch.ge.hes.delbiaggio.biblio.composant;

import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.UI;

import ch.ge.hes.delbiaggio.biblio.BiblioUI;
import ch.ge.hes.delbiaggio.biblio.listener.menuBar.ConnectionPress;
import ch.ge.hes.delbiaggio.biblio.listener.menuBar.DeconnexionPressed;
import ch.ge.hes.delbiaggio.biblio.listener.menuBar.LivresPressed;

import com.vaadin.ui.HorizontalLayout;

public class ComposantBarMenu extends HorizontalLayout{
	private Label lbNampeApp; 
	private Button btnLivres; 
	private Button btnConnexion;
	
	public ComposantBarMenu() {
		initBarMenu();
		BiblioUI ui = (BiblioUI)UI.getCurrent();
		addComponents(lbNampeApp,btnLivres);
		Object user = VaadinService.getCurrentRequest().getWrappedSession().getAttribute("username"); 
		if(user == null){				
				btnConnexion = new Button("Connexion");
				btnConnexion.addClickListener(new ConnectionPress());
		}else{
				Label l = new Label(user.toString());
				addComponent(l);
				setComponentAlignment(l, Alignment.TOP_RIGHT);
				btnConnexion = new Button("Déconnexion");
				btnConnexion.addClickListener(new DeconnexionPressed());				
		}
		addComponent(btnConnexion);
		setComponentAlignment(btnLivres, Alignment.TOP_CENTER);
		setComponentAlignment(btnConnexion, Alignment.TOP_RIGHT);		
	}
		
	private void initBarMenu(){
		setMargin(true);
		setWidth("100%");
		lbNampeApp = new Label("App Bibliothèque");
		btnLivres = new Button("Livres");
		btnLivres.setStyleName("link");
		btnLivres.addClickListener(new LivresPressed());		
	}
}
