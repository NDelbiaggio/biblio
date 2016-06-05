package ch.ge.hes.delbiaggio.biblio.listener.ecranNouveauLivre;


import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;

import ch.ge.hes.delbiaggio.biblio.domaine.Livre;
import ch.ge.hes.delbiaggio.biblio.ecran.EcranNouveauLivre;

public class FieldNewBookChanged implements Property.ValueChangeListener{
	
	private Livre book;
	private Button btnCreate;
	
	public FieldNewBookChanged(Livre book,Button btnCreate) {
		this.book = book;
		this.btnCreate = btnCreate;
	}

	public void valueChange(ValueChangeEvent event) {
			EcranNouveauLivre ecran = (EcranNouveauLivre)UI.getCurrent().getContent();
			if(!(book.getTitre()==null || book.getAuteur()==null || book.getEditeur()==null /*|| book.getAnnee()== -1*/)){

	        	btnCreate.setEnabled(true);
			}else{
				btnCreate.setEnabled(false);
			}	
	}	
}
