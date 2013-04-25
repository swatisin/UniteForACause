package model;


import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.*;

public class CharityDAO extends GenericDAO<CharityBean> {
	public CharityDAO( String tableName, ConnectionPool cp) throws DAOException {
		super(CharityBean.class, tableName, cp);
	}

public void addToTop(CharityBean item) throws RollbackException {
		try {
			Transaction.begin();
			
			// Get item at top of list
			CharityBean[] a = match(MatchArg.min("position"));
			
			CharityBean topBean;
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
			if (item.getTitle() != null)
				createAutoIncrement(item);

			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public void addToBottom(CharityBean item) throws RollbackException {
		try {
			Transaction.begin();
			
			// Get item at bottom of list
			CharityBean[] a = match(MatchArg.max("position"));
			
			CharityBean bottomBean;
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
	
	public CharityBean[] getItems() throws RollbackException {
		
		// Calls GenericDAO's match() method.
		// This no match constraint arguments, match returns all the Item beans 
		CharityBean[] items = match();
        
        // Sort the list in position order
        java.util.Arrays.sort(items, new Comparator<CharityBean>() {
        	public int compare(CharityBean item1, CharityBean item2) {
        		return item1.getPosition() - item2.getPosition();
        	}
		});
        
        return items;
	}
	public CharityBean[] getUserCharity(String userName) throws RollbackException {
		
		// Calls GenericDAO's match() method.
		// This no match constraint arguments, match returns all the Item beans 
		CharityBean[] items = match(MatchArg.equals("userName",userName));
		
        
        // Sort the list in position order
        java.util.Arrays.sort(items, new Comparator<CharityBean>() {
        	public int compare(CharityBean item1, CharityBean item2) {
        		return item1.getPosition() - item2.getPosition();
        	}
		});
        
        return items;
	}
	
	public CharityBean getItem( String eventID) throws RollbackException {
		
		if(eventID == null)
			return null;
		int eventId = Integer.parseInt(eventID);	

		// Calls GenericDAO's match() method.
		// This no match constraint arguments, match returns all the Item beans 
		CharityBean[] items = match(MatchArg.equals("id",eventId));
        
        // Sort the list in position order
        java.util.Arrays.sort(items, new Comparator<CharityBean>() {
        	public int compare(CharityBean item1, CharityBean item2) {
        		return item1.getPosition() - item2.getPosition();
        	}
		});
        
        return items[0];
	}
	
	public void addDonationsToDatabase() throws RollbackException {
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
			 List<String> description = new LinkedList<String>();
			 description.add("Provided via Organization X ");
			 description.add("Provided via Organization Y ");
			 description.add("Provided via Organization Z ");
			 description.add("Provided via Organization W ");


			Random rand = new Random();
			Transaction.begin();
			
			// Get item at top of list
			//java.sql.Date sqlToday = new java.sql.Date(today.getTime() + i );
			         
			
			CharityBean donation;
			donation = new CharityBean();
			for ( int i = 0 ; i < 10 ; i++) {
				int choice = rand.nextInt(category.size());
		        //System.out.println("Choice = " + my_words.get(choice));
				donation.setCategory(category.get(choice));
				donation.setTitle(eventTitle.get(choice));
				donation.setDescription(description.get(choice));
				donation.setAmount(choice * 10); 
				createAutoIncrement(donation);

			}
			

			// Create a new BlogBean in the database with the next id number

			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
		
		
	}
	public double getSum() throws RollbackException {
		double sum = 0;
		// Calls GenericDAO's match() method.
		// This no match constraint arguments, match returns all the Item beans 
		CharityBean[] items = match();
		
		for ( int i = 0; i < items.length ; i ++)
			sum = sum + items[i].getAmount();
        
        // Sort the list in position order
        java.util.Arrays.sort(items, new Comparator<CharityBean>() {
        	public int compare(CharityBean item1, CharityBean item2) {
        		return item1.getPosition() - item2.getPosition();
        	}
		});
        
        return sum;
	}

}
