package model;


import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;
import java.text.*;
import java.util.*;
import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.*;

public class RegisterEventDAO extends GenericDAO<RegisterEventBean> {
	public RegisterEventDAO( String tableName, ConnectionPool cp) throws DAOException {
		super(RegisterEventBean.class, tableName, cp);
	}
	
	
	public void addToTop(RegisterEventBean item) throws RollbackException {
		try {
			Transaction.begin();
			
			
			// Create a new BlogBean in the database with the next id number
			createAutoIncrement(item);

			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	

	
	public RegisterEventBean[] getEvents() throws RollbackException {
		
		// Calls GenericDAO's match() method.
		// This no match constraint arguments, match returns all the Item beans 
		RegisterEventBean[] items = match();
        
        // Sort the list in position order
        
        return items;
	}
	public RegisterEventBean[] getUserRegistration(String userName) throws RollbackException {
		
		// Calls GenericDAO's match() method.
		// This no match constraint arguments, match returns all the Item beans 
		RegisterEventBean[] items = match(MatchArg.equals("userName",userName));
		
        // Sort the list in position order       
        
        return items;
	}


}
