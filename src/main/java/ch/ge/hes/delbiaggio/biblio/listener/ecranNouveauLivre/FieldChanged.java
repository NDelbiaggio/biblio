package ch.ge.hes.delbiaggio.biblio.listener.ecranNouveauLivre;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;

import ch.ge.hes.delbiaggio.biblio.domaine.Livre;
import ch.ge.hes.delbiaggio.biblio.domaine.Utilisateur;

public class FieldChanged implements Property.ValueChangeListener{
	private Livre book;
	
	public FieldChanged(Livre book){
		this.book = book;
	}

	public void valueChange(ValueChangeEvent event) {
		Field<?> tf = (Field<?>)event.getProperty();
		if (tf.isValid()) {
			tf.commit();		
		}		
	}

	
}
