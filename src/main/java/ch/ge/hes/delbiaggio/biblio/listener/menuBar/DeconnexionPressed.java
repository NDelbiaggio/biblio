package ch.ge.hes.delbiaggio.biblio.listener.menuBar;

import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;

import ch.ge.hes.delbiaggio.biblio.BiblioUI;
import ch.ge.hes.delbiaggio.biblio.ecran.EcranAccueil;
import ch.ge.hes.delbiaggio.biblio.ecran.EcranConnexion;

public class DeconnexionPressed implements Button.ClickListener{
	public void buttonClick(ClickEvent event) {
		VaadinService.getCurrentRequest().getWrappedSession().setAttribute("username", null);
		UI.getCurrent().setContent(new EcranAccueil());
	}
}
