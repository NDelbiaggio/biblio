package ch.ge.hes.delbiaggio.biblio.ecran;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ChoiceFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;


import com.sun.corba.se.impl.transport.CorbaConnectionCacheBase;
import com.vaadin.data.Container.Indexed;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.util.PropertyValueGenerator;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.data.util.sqlcontainer.RowId;
import com.vaadin.data.util.sqlcontainer.RowItem;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.TableQuery;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.HeaderCell;
import com.vaadin.ui.Grid.HeaderRow;
import com.vaadin.ui.TextField;

import ch.ge.hes.delbiaggio.biblio.bdd.ConnexionBase;
import ch.ge.hes.delbiaggio.biblio.composant.ComposantBarMenu;
import ch.ge.hes.delbiaggio.biblio.domaine.Livre;
import ch.ge.hes.delbiaggio.biblio.listener.ecranListeLivre.FilterTextChangeListener;
import ch.ge.hes.delbiaggio.biblio.listener.ecranListeLivre.NewBookPressed;
import ch.ge.hes.delbiaggio.biblio.listener.menuBar.ConnectionPress;
import ch.ge.hes.delbiaggio.biblio.listener.menuBar.DeconnexionPressed;

import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.NumberRenderer;


public class EcranListeLivre extends VerticalLayout {

	private Grid grLstLivre;
	private Button btnNewBook;
	private JDBCConnectionPool pool;

	public EcranListeLivre() {
		setMargin(true);
		setSpacing(true);
		try {
			pool = ConnexionBase.connexionBDD();
		} catch (SQLException e) {
			new RuntimeException(e);
		}
		Component c = initMenuBar();
		loadBooks();
		initGrid();
		this.setComponentAlignment(grLstLivre, Alignment.TOP_CENTER);		
		initBtnNouveau();		
		setSizeFull();
	}

	private Component initMenuBar() {
		ComposantBarMenu mnuBar = new ComposantBarMenu();
		addComponent(mnuBar);
		return mnuBar;
	}

	private void loadBooks() {
		TableQuery query = new TableQuery("LIVRE", pool);
		try {
			SQLContainer container = new SQLContainer(query);
			GeneratedPropertyContainer wrapper = new GeneratedPropertyContainer(container);
			wrapper.addGeneratedProperty("delete", new PropertyValueGenerator<Integer>() {

				public Integer getValue(Item item, Object itemId, Object propertyId) {
					Property<Integer> property = item.getItemProperty("NOLIVRE");
					return property.getValue();
				}

				public Class<Integer> getType() {
					// TODO Auto-generated method stub
					return Integer.class;
				}
			});
			grLstLivre = new Grid("", wrapper);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void initGrid() {
		grLstLivre.setSizeFull();		
		grLstLivre.removeColumn("NOLIVRE");
		grLstLivre.setColumnOrder("TITRE", "AUTEUR", "EDITEUR", "ANNEE");
		// grLstLivre.setHeight("100%");
		// grLstLivre.setHeight("400px");
		grLstLivre.getColumn("ANNEE").setRenderer(new NumberRenderer(new DecimalFormat("####")));
		grLstLivre.setHeightByRows(20);
		addFiltering();
		addComponent(grLstLivre);
		setExpandRatio(grLstLivre, 1.0f);		
		setComponentAlignment(grLstLivre, Alignment.TOP_LEFT);
	}

	private void addFiltering() {
		GeneratedPropertyContainer container = (GeneratedPropertyContainer) grLstLivre.getContainerDataSource();
		HeaderRow filterRow = grLstLivre.appendHeaderRow();
		for (Object pid : grLstLivre.getContainerDataSource().getContainerPropertyIds()) {
			if (!(pid.toString().equals("NOLIVRE") || pid.toString().equals("DELETE"))) {
				HeaderCell cell = filterRow.getCell(pid);
				TextField filterField = new TextField();
				filterField.setHeight("18px");
				filterField.addTextChangeListener(new FilterTextChangeListener(container, pid));
				cell.setComponent(filterField);
			}
		}
	}

	private void initBtnNouveau() {
		if (!(VaadinService.getCurrentRequest().getWrappedSession().getAttribute("username") == null)) {
			btnNewBook = new Button("Nouveau");
			btnNewBook.addClickListener(new NewBookPressed());
			btnNewBook.setWidth("10%");
			addComponent(btnNewBook);
			setComponentAlignment(btnNewBook, Alignment.TOP_RIGHT);
		}
	}

	@SuppressWarnings("unchecked")
	public void insertBook(Livre book) {
		GeneratedPropertyContainer gc = (GeneratedPropertyContainer)grLstLivre.getContainerDataSource();		
		SQLContainer cont = (SQLContainer) gc.getWrappedContainer();
		Object id = cont.addItem();
		Item i = cont.getItem(id);
		i.getItemProperty("TITRE").setValue(book.getTitre());
		i.getItemProperty("AUTEUR").setValue(book.getAuteur());
		i.getItemProperty("EDITEUR").setValue(book.getEditeur());
		i.getItemProperty("ANNEE").setValue(book.getYear());		
		try {
			cont.commit();
		} catch (SQLException e) {
			new RuntimeException(e);
		}
	}
}
