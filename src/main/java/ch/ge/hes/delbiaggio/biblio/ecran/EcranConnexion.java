package ch.ge.hes.delbiaggio.biblio.ecran;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import ch.ge.hes.delbiaggio.biblio.composant.ComposantBarMenu;
import ch.ge.hes.delbiaggio.biblio.composant.ComposantBarMenuWthConnexion;
import ch.ge.hes.delbiaggio.biblio.domaine.Utilisateur;
import ch.ge.hes.delbiaggio.biblio.listener.ecranConnexion.PasswordFieldChanged;
import ch.ge.hes.delbiaggio.biblio.listener.ecranConnexion.UsernameFieldChanged;
import ch.ge.hes.delbiaggio.biblio.listener.ecranConnexion.ValidationConnexion;

@SuppressWarnings("serial")
public class EcranConnexion extends VerticalLayout{
	
	@PropertyId("username")
	private Field<?> tfLogin;
	@PropertyId("password")
	private PasswordField tfPassword;	
	private Button btnConnecter;	
	private Utilisateur user = new Utilisateur();
	private BeanItem<Utilisateur> item = new BeanItem<Utilisateur>(user);

    
    public EcranConnexion(){
    	addComponent(new ComposantBarMenuWthConnexion());
    	initComponents();
    	initFormLayout();	
        setMargin(true);
		setWidth("100%");
    }
    
	private void initComponents(){
    	FieldGroup groupe = new FieldGroup(item);    	
    	tfLogin = groupe.buildAndBind("Username","username");
    	((TextField) tfLogin).setNullRepresentation("");
    	tfLogin.addValueChangeListener(new UsernameFieldChanged(user));
    	tfPassword = new PasswordField("Password");
    	groupe.bind(tfPassword, "password");
    	tfPassword.setNullRepresentation("");
    	tfPassword.addValueChangeListener(new PasswordFieldChanged(user));
    	btnConnecter = new Button("Se connecter");
    	btnConnecter.addClickListener(new ValidationConnexion(user));
    }
		
    
    private void initFormLayout(){
    	FormLayout fl = new FormLayout(tfLogin,tfPassword,btnConnecter);
    	fl.setMargin(true);
    	fl.setStyleName("form");
    	fl.setWidth("100%");	
    	addComponents(fl);
    }

}
