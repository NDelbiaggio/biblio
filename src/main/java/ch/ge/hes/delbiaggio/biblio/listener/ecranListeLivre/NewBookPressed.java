package ch.ge.hes.delbiaggio.biblio.listener.ecranListeLivre;

import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import ch.ge.hes.delbiaggio.biblio.ecran.EcranNouveauLivre;

public class NewBookPressed implements Button.ClickListener{
	public void buttonClick(ClickEvent event) {
		UI.getCurrent().setContent(new EcranNouveauLivre());
	}
}
