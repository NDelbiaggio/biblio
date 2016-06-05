package ch.ge.hes.delbiaggio.biblio.listener.menuBar;

import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import ch.ge.hes.delbiaggio.biblio.ecran.EcranListeLivre;

public class LivresPressed implements Button.ClickListener {
	public void buttonClick(ClickEvent event) {
		UI ui = UI.getCurrent();
		ui.setContent(new EcranListeLivre());
	}
}
