package ch.ge.hes.delbiaggio.biblio.bdd;

import java.sql.Connection;
import java.sql.SQLException;

import com.vaadin.data.util.filter.And;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.TableQuery;



public class ConnexionBase {
	
	public static JDBCConnectionPool connexionBDD() throws SQLException {
		JDBCConnectionPool pool = new SimpleJDBCConnectionPool("org.h2.Driver", "jdbc:h2:~/test", "sa", "", 2, 5);
		return pool;
	}
	
	public static boolean checkUser(String login, String password){
		try {
			JDBCConnectionPool p = connexionBDD();
			TableQuery query = new TableQuery("utilisateur",p);
			SQLContainer contUser = new SQLContainer(query);
			contUser.addContainerFilter(new And(new Compare.Equal("UTI_LOGIN", login),new Compare.Equal("UTI_PASSWORD", password)));									
			return  contUser.getItemIds().size() > 0;			
		} catch (SQLException e) {
			new RuntimeException(e);
		}
		return false;
	}
} 
