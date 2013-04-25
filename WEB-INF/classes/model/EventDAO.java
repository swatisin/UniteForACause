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

public class EventDAO extends GenericDAO<EventBean> {
	public EventDAO( String tableName, ConnectionPool cp) throws DAOException {
		super(EventBean.class, tableName, cp);
	}
	
	public void addEventsToDatabase() throws RollbackException {
		try {
			 List<String> category = new LinkedList<String>();
			 category.add("Food");
			 category.add("Education");
			 category.add("Children Care");
			 category.add("Old Age Help");
			 List<String> eventTitle = new LinkedList<String>();
			 eventTitle.add("Africa Food Mission");
			 eventTitle.add("Educate the Children");
			 eventTitle.add("Health Issues Children Face");
			 eventTitle.add("Old Age Help center");


			Random rand = new Random();
			Transaction.begin();
			
			// Get item at top of list
			java.util.Date today = new java.util.Date();
			//java.sql.Date sqlToday = new java.sql.Date(today.getTime() + i );
			         
			
			EventBean event;
			event = new EventBean();
			for ( int i = 0 ; i < 10 ; i++) {
				int choice = rand.nextInt(category.size());
		        //System.out.println("Choice = " + my_words.get(choice));
				event.setCategory(category.get(choice));
				java.sql.Date sqlToday = new java.sql.Date(today.getTime() + i * 24 * 60 * 60 * 1000);
				event.setEventDate(sqlToday );
				event.setTitle(eventTitle.get(choice));
				createAutoIncrement(event);

			}
			

			// Create a new BlogBean in the database with the next id number

			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
		
		
	}
	public void addToTop(EventBean item) throws RollbackException {
		try {
			Transaction.begin();
			
			// Get item at top of list
			EventBean[] a = match(MatchArg.min("position"));
			
			EventBean topBean;
			if (a.length == 0) {
				topBean = null;
			} else {
				topBean = a[0];
			}
			
			int newPos;
			if (topBean == null) {
				// List is empty...just add it with position = 1
				newPos = 1;
			} else {
				// Create the new item with position one less than the top bean's position
				newPos = topBean.getPosition() - 1;
			}

			item.setPosition(newPos);

			// Create a new BlogBean in the database with the next id number
			createAutoIncrement(item);

			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public void addToBottom(EventBean item) throws RollbackException {
		try {
			Transaction.begin();
			
			// Get item at bottom of list
			EventBean[] a = match(MatchArg.max("position"));
			
			EventBean bottomBean;
			if (a.length == 0) {
				bottomBean = null;
			} else {
				bottomBean = a[0];
			}
			
			int newPos;
			if (bottomBean == null) {
				// List is empty...just add it with position = 1
				newPos = 1;
			} else {
				// Create the new item with position one more than the bottom bean's position
				newPos = bottomBean.getPosition() + 1;
			}

			item.setPosition(newPos);

			// Create a new BlogBean in the database with the next id number
			createAutoIncrement(item);

			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public EventBean[] getEvents() throws RollbackException {
		
		// Calls GenericDAO's match() method.
		// This no match constraint arguments, match returns all the Item beans 
		EventBean[] items = match();
        
        // Sort the list in position order
        java.util.Arrays.sort(items, new Comparator<EventBean>() {
        	public int compare(EventBean item1, EventBean item2) {
        		return item1.getPosition() - item2.getPosition();
        	}
		});
        
        return items;
	}
	
	public EventBean[] getUserEvents(String userName) throws RollbackException {
		
		// Calls GenericDAO's match() method.
		// This no match constraint arguments, match returns all the Item beans 
		EventBean[] items = match(MatchArg.equals("userName",userName));
		
        
        // Sort the list in position order
        java.util.Arrays.sort(items, new Comparator<EventBean>() {
        	public int compare(EventBean item1, EventBean item2) {
        		return item1.getPosition() - item2.getPosition();
        	}
		});
        
        return items;
	}
public EventBean getEvent(String eventID) throws RollbackException {
		
		// Calls GenericDAO's match() method.
		// This no match constraint arguments, match returns all the Item beans 
	int eventId = Integer.parseInt(eventID);	
	
	EventBean[] items = match(MatchArg.equals("id",eventId));
		
        
        // Sort the list in position order
        java.util.Arrays.sort(items, new Comparator<EventBean>() {
        	public int compare(EventBean item1, EventBean item2) {
        		return item1.getPosition() - item2.getPosition();
        	}
		});
        
        return items[0];
	}


}
