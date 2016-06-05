package ch.ge.hes.delbiaggio.biblio.listener.ecranListeLivre;

import com.vaadin.data.Container.Filter;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;

public class FilterTextChangeListener implements TextChangeListener {

	private GeneratedPropertyContainer container;
	private Object id;
	
	public FilterTextChangeListener(GeneratedPropertyContainer container, Object id) {
		this.container = container;
		this.id = id;
	}
	
	
	public void textChange(TextChangeEvent event) {
		SQLContainer sqlcont = (SQLContainer)container.getWrappedContainer();
		sqlcont.removeContainerFilters(id);
		if (!event.getText().isEmpty()){
			container.addContainerFilter(new SimpleStringFilter(id, event.getText(), true, false));			
		}		
	}

}
