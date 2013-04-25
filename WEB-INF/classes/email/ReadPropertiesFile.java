package email;

import java.io.FileInputStream;
import java.util.Properties;

import com.sun.tools.javac.util.List;

import email.Constants;
import databeans.signupEmail;
import model.emailDAO;
 
public class ReadPropertiesFile
{
	
    public  static void readConfig() throws Exception
    {
        try
        {
 
            Properties pro = new Properties();
            String path = "./java4s_Props.properties";
            System.out.println(path);
            pro.load(new FileInputStream(path));       
 
            Constants.delay = "7d";
            System.out.println(pro.getProperty("delay"));
            Constants.timetoquery = "2s";
            System.out.println(pro.getProperty("timetoquery"));
            Constants.setFrom = pro.getProperty("setFrom");
            Constants.setPassword = pro.getProperty("setPassword");
            Constants.emailTO = Constants.getEmailTO();            
 
        }
        catch(Exception e)
        {
            throw new Exception(e);
        }
 
    }
   
}