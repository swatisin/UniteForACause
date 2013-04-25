package model;


import java.util.Comparator;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.*;

public class BlogDAO extends GenericDAO<BlogBean> {
	public BlogDAO( String tableName, ConnectionPool cp) throws DAOException {
		super(BlogBean.class, tableName, cp);
	}
	
	public void addToTop(BlogBean item) throws RollbackException {
		try {
			Transaction.begin();
			
			// Get item at top of list
			BlogBean[] a = match(MatchArg.min("position"));
			
			BlogBean topBean;
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
	
	public void addToBottom(BlogBean item) throws RollbackException {
		try {
			Transaction.begin();
			
			// Get item at bottom of list
			BlogBean[] a = match(MatchArg.max("position"));
			
			BlogBean bottomBean;
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
	
	public BlogBean[] getBlogs() throws RollbackException {
		
		// Calls GenericDAO's match() method.
		// This no match constraint arguments, match returns all the Item beans 
		BlogBean[] items = match();
        
        // Sort the list in position order
        java.util.Arrays.sort(items, new Comparator<BlogBean>() {
        	public int compare(BlogBean item1, BlogBean item2) {
        		return item1.getPosition() - item2.getPosition();
        	}
		});
        
        return items;
	}
	public BlogBean[] getUserBlogs(String userName) throws RollbackException {
		
		// Calls GenericDAO's match() method.
		// This no match constraint arguments, match returns all the Item beans 
		BlogBean[] items = match(MatchArg.equals("userName",userName));
		
        
        // Sort the list in position order
        java.util.Arrays.sort(items, new Comparator<BlogBean>() {
        	public int compare(BlogBean item1, BlogBean item2) {
        		return item1.getPosition() - item2.getPosition();
        	}
		});
        
        return items;
	}
	
	public BlogBean[] searchBlog(String category) throws RollbackException {
		
		// Calls GenericDAO's match() method.
		// This no match constraint arguments, match returns all the Item beans 
		BlogBean[] items = match(MatchArg.equals("category",category));
		
        
        // Sort the list in position order
        java.util.Arrays.sort(items, new Comparator<BlogBean>() {
        	public int compare(BlogBean item1, BlogBean item2) {
        		return item1.getPosition() - item2.getPosition();
        	}
		});
        
        return items;
	}
}
