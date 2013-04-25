package email;

import java.util.TimerTask;
import email.Constants;
 
public class Testing extends TimerTask
{
 
    public void run()
    {
 
                GMailServer sender = new GMailServer(Constants.setFrom, Constants.setPassword);
 
                try {
                sender.sendMail("Weekly Newsletter From UniteForACause","Weekly Newsletter From UniteForACause",Constants.setFrom,Constants.emailTO);
            }
                   catch (Exception e) {
                 e.printStackTrace();
            }  
 
                System.out.println("Email Sent Succesfully...");
 
            }
}