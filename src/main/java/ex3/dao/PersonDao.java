package ex3.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import ex3.Person;

public class PersonDao extends AbstractDao {

    public List<Person> printPersons() {
    	List<Person> persons = new ArrayList<Person>();
    	
    	try {
    		st = getConnection().createStatement();
    		rs = st.executeQuery("select * from person");
    		
    		while (rs.next()) {
    			Person person = new Person();
    			person.setName(rs.getString(1));
    			persons.add(person);
    		}
    		
    	} catch (Exception e) {
    		throw new RuntimeException(e);
    	} finally {
    		closeResources();
    	}
    	
    	return persons;
    }

	
}
