package model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.RollbackException;

public class Model {
	private UserDAO  userDAO;
	private BlogDAO blogDAO;
	private EventDAO eventDAO;
	private CharityDAO charityDAO;
	private emailDAO EmailDAO;
	private RegisterEventDAO registerDAO;
	private DonationDAO donationDAO;


	public Model(ServletConfig config) throws ServletException {
		try {
			String jdbcDriver = config.getInitParameter("jdbcDriverName");
			String jdbcURL    = config.getInitParameter("jdbcURL");
			
			ConnectionPool pool = new ConnectionPool(jdbcDriver, jdbcURL);
			userDAO  = new UserDAO("user", pool);
			blogDAO = new BlogDAO("blog", pool);
			eventDAO = new EventDAO("events", pool);
			charityDAO = new CharityDAO("donations", pool);
			EmailDAO = new emailDAO("signup", pool);
			registerDAO = new RegisterEventDAO("userRegisterEvents", pool);
			donationDAO = new DonationDAO("fixDonations", pool);
			try {
			eventDAO.addEventsToDatabase();
			charityDAO.addDonationsToDatabase();
			} catch (RollbackException e) {
	        	System.out.println(e.getMessage());
	        	//return "error.jsp";
	        }

			
		} catch (DAOException e) {
			throw new ServletException(e);
		}
	}
	
	public UserDAO  getUserDAO()  { return userDAO;  }
	public BlogDAO getBlogDAO() { return blogDAO; }
	public EventDAO getEventDAO() { return eventDAO; }
	public CharityDAO getCharityDAO() { return charityDAO; }
	public emailDAO getEmailDAO() { return EmailDAO; }
	public RegisterEventDAO getRegisterEventDAO() { return registerDAO; }
	public DonationDAO getDonationDAO() { return donationDAO; }




	
}
