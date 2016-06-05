package ch.ge.hes.delbiaggio.biblio.listener.ecranNouveauLivre;

import java.sql.Connection;
import java.sql.Statement;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

import ch.ge.hes.delbiaggio.biblio.domaine.Livre;
import ch.ge.hes.delbiaggio.biblio.ecran.EcranListeLivre;
import ch.ge.hes.delbiaggio.biblio.ecran.EcranNouveauLivre;

@SuppressWarnings("serial")
public class CreerLivreAppuyeListener implements Button.ClickListener {
	
	private Livre book;
	
	public CreerLivreAppuyeListener(Livre book) {
		this.book = book;
	}

	public void buttonClick(ClickEvent event) {
		EcranListeLivre ecran = new EcranListeLivre();
		ecran.insertBook(book);
        Notification.show("Livre ajouté !");
        UI.getCurrent().setContent(ecran); 
	}
	
	
	
}
