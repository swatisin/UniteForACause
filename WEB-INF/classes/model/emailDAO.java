package model;


import java.util.Comparator;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.*;

public class emailDAO extends GenericDAO<signupEmail> {
	public emailDAO( String tableName, ConnectionPool cp) throws DAOException {
		super(signupEmail.class, tableName, cp);
	}
	
	public signupEmail[] getItems() throws RollbackException {
		
		// Calls GenericDAO's match() method.
		// This no match constraint arguments, match returns all the Item beans 
		signupEmail[] items = match();
        
        // Sort the list in position order
        
        return items;
	}
	public void addForSignup(signupEmail item) throws RollbackException {
		try {
			Transaction.begin();
			
			// Get item at top of list
			
			// Create a new BlogBean in the database with the next id number
			createAutoIncrement(item);

			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	

}
