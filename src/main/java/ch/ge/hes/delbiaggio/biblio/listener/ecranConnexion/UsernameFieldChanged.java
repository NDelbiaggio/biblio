package ch.ge.hes.delbiaggio.biblio.listener.ecranConnexion;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;

import ch.ge.hes.delbiaggio.biblio.domaine.Utilisateur;

public class UsernameFieldChanged implements Property.ValueChangeListener{

	private Utilisateur user;
	
	public UsernameFieldChanged(Utilisateur user){
		this.user = user;
	}
		
	
	public void valueChange(ValueChangeEvent event) {
		user.setUsername(event.getProperty().getValue()+"");		
	}

}
