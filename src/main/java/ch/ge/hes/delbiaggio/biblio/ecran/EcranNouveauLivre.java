package ch.ge.hes.delbiaggio.biblio.ecran;

import java.beans.Beans;


import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import ch.ge.hes.delbiaggio.biblio.composant.ComposantBarMenu;
import ch.ge.hes.delbiaggio.biblio.domaine.Livre;
import ch.ge.hes.delbiaggio.biblio.listener.ecranNouveauLivre.CreerLivreAppuyeListener;
import ch.ge.hes.delbiaggio.biblio.listener.ecranNouveauLivre.FieldChanged;
import ch.ge.hes.delbiaggio.biblio.listener.ecranNouveauLivre.FieldNewBookChanged;

@SuppressWarnings("serial")
public class EcranNouveauLivre extends VerticalLayout{
	private FormLayout fl;
	private FieldGroup groupe;
	private Field<?> tfTitre; 
	private Field<?> tfAuteur;
	private Field<?> tfEditeur;	
	private Field<?> tfAnnee;	
	private Button btnCree;	
	private Livre book = new Livre();
	
	
	public EcranNouveauLivre(){
		setMargin(true);
		setSizeFull();	
		initComponent();
		ComposantBarMenu menubar = new ComposantBarMenu();
		addComponent(menubar);
		addComponent(fl);
		setExpandRatio(fl, 1.0f);
		setComponentAlignment(fl, Alignment.TOP_CENTER);
	}
	
	private void initComponent(){
		BeanItem <Livre> item = new BeanItem<Livre>(book);
		groupe = new FieldGroup(item);
		initBtnCree();
		initTfTitre();
		initTfAuteur();
		initTfEditeur();
		initTfAnnee();		
		initFl();
	}
	
	@SuppressWarnings("unchecked")
	private void initTfTitre(){		
		tfTitre = groupe.buildAndBind("titre");		
		tfTitre.addValueChangeListener(new FieldChanged(book));
		tfTitre.addValueChangeListener(new FieldNewBookChanged(book,btnCree));
		((AbstractTextField) tfTitre).setNullRepresentation("");
		tfTitre.setRequired(true);
	}
	
	private void initTfAuteur(){
		tfAuteur = groupe.buildAndBind("auteur");
		tfAuteur.addValueChangeListener(new FieldChanged(book));
		tfAuteur.addValueChangeListener(new FieldNewBookChanged(book,btnCree));
		tfAuteur.addValidator(new StringLengthValidator("Nb de caractères doit être compris entre [1 - 50]",1, 50, true));
		((AbstractTextField) tfAuteur).setNullRepresentation("");
		tfAuteur.setRequired(true);
	}
	
	private void initTfEditeur(){
		tfEditeur = groupe.buildAndBind("editeur");
		tfEditeur.addValueChangeListener(new FieldChanged(book));
		tfEditeur.addValueChangeListener(new FieldNewBookChanged(book,btnCree));
		tfEditeur.addValidator(new StringLengthValidator("Nb de caractères doit être compris entre [1 - 50]",1, 50, true));		
		((AbstractTextField) tfEditeur).setNullRepresentation("");
		tfEditeur.setRequired(true);
	}
	
	private void initTfAnnee(){
		tfAnnee = new DateField("Annee");		
		tfAnnee.setRequired(true);
		((DateField)tfAnnee).setResolution(Resolution.YEAR);
		groupe.bind(tfAnnee, "annee");
		((DateField)tfAnnee).setDateFormat("yyyy");		
		tfAnnee.addValueChangeListener(new FieldChanged(book));
		tfAnnee.addValueChangeListener(new FieldNewBookChanged(book,btnCree));		
		tfAnnee.addValidator(new RegexpValidator(/*"^[0-9]{4}$"*/ "[0-9][0-9]{3}","Vous devez entrer une année valide au format 'AAAA'!"));
		//((AbstractField<Integer>) tfAnnee).setValidationVisible(true);
		//((AbstractTextField) tfAnnee).setNullRepresentation("");
		//((AbstractComponent) tfAnnee).setImmediate(true);
		//((AbstractTextField) tfAnnee).setInputPrompt("Entrez une année");
		//((DateField)tfAnnee).setResolution(Resolution.YEAR);
	}
	
	private void initBtnCree(){		
		btnCree = new Button("Créer");		
		btnCree.setEnabled(false);
		btnCree.setClickShortcut(KeyCode.ENTER);
		btnCree.addClickListener(new CreerLivreAppuyeListener(book));
	}
	
	private void initFl(){
		fl = new FormLayout(tfTitre,tfAuteur,tfEditeur,tfAnnee,btnCree);
		fl.setComponentAlignment(btnCree, Alignment.MIDDLE_RIGHT);
	}
	
}
