package ch.ge.hes.delbiaggio.biblio.listener.ecranConnexion;

import com.vaadin.server.VaadinService;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import ch.ge.hes.delbiaggio.biblio.BiblioUI;
import ch.ge.hes.delbiaggio.biblio.bdd.ConnexionBase;
import ch.ge.hes.delbiaggio.biblio.domaine.Utilisateur;
import ch.ge.hes.delbiaggio.biblio.ecran.EcranAccueil;
import ch.ge.hes.delbiaggio.biblio.ecran.EcranConnexion;

@SuppressWarnings("serial")
public class ValidationConnexion implements Button.ClickListener {

	private Utilisateur user;
	
	public ValidationConnexion(Utilisateur user){
		this.user = user;
	}
		
	public void buttonClick(ClickEvent event) {
		UI.getCurrent().getContent();
		if(ConnexionBase.checkUser(user.getUsername(), user.getPassword())){	
			VaadinService.getCurrentRequest().getWrappedSession().setAttribute("username", user.getUsername());
			UI.getCurrent().setContent(new EcranAccueil());
		}else{
			UI.getCurrent().setContent(new EcranConnexion());
			Notification.show("Identifiant / mot de passe erroné!",Notification.Type.WARNING_MESSAGE);
		}
	}
}
