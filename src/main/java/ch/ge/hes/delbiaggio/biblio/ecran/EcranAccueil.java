package ch.ge.hes.delbiaggio.biblio.ecran;
import java.io.File;

import com.vaadin.server.FileResource;
import com.vaadin.server.ClassResource;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;

import ch.ge.hes.delbiaggio.biblio.composant.ComposantBarMenu;

public class EcranAccueil extends VerticalLayout{
	private VerticalLayout vl;
	private HorizontalLayout hl = new HorizontalLayout();
	private final Label lblBienvenue = new Label("Bienvenue dans votre bibliothèque virtuelle");
	private final Label lblDescription = new Label("Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum");	

	public EcranAccueil(){		
		initVerticalLayout();
		initHorizontalLayout();
		addComponent(new ComposantBarMenu());
		addComponent(hl);
		setMargin(true);
		setWidth("100%");
	}
	
	private void initHorizontalLayout(){
		String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
		FileResource resource = new FileResource(new File(basepath +"/WEB-INF/img/Livre.png"));
		Image  image = new Image ("", resource);
		image.setStyleName("imgBibli");
		hl.addComponents(vl,image);
		hl.setMargin(true);
		hl.setWidth("90%");
	}
	
	private void initVerticalLayout(){
		vl = new VerticalLayout();
		vl.addComponents(lblBienvenue,new Label("&nbsp;", ContentMode.HTML),lblDescription);
		vl.setMargin(true);
	}
	

}
