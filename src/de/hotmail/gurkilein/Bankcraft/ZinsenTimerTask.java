package de.hotmail.gurkilein.Bankcraft;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.TimerTask;
import org.bukkit.entity.Player;

public class ZinsenTimerTask extends TimerTask{
	  FileWriter writer;
	  public void run()  {
	  try {
		//  GELDzinsen
		  
		  File fB;
		  File fA = new File("plugins"+System.getProperty("file.separator")+"Bankcraft"+System.getProperty("file.separator")+"Accounts");
	       Player[] playerArray = Bankcraft.server.getOnlinePlayers();
		  File[] fileArray = fA.listFiles();
		  if (fileArray != null) {
			for (int i = 0; i < fileArray.length; i++) {
				if (configHandler.onlinemoney == false || Bankcraft.server.getPlayer(fileArray[i].getName().toString().split(".db")[0]) != null) {
				File file = fileArray[i];
		         FileReader fr = new FileReader(file);
		            BufferedReader reader = new BufferedReader(fr);
		            String st = reader.readLine(); 
			            	Double betrag = new Double(st)*(configHandler.interest+1);
			            	DecimalFormat df= new DecimalFormat("#0.00");   
			            	String betragstring= df.format(betrag);  
			            	betrag = Double.parseDouble(betragstring);  
		   fr.close();
		   reader.close();
        writer = new FileWriter(file);
	       writer.write(betrag+System.getProperty("line.separator"));
	       writer.flush();
	       writer.close();
	       if (configHandler.broadcast == true) {
	       Double zinsen = new Double(st)*(configHandler.interest);
       	DecimalFormat dft= new DecimalFormat("#0.00");   
       	String betragstringt= dft.format(zinsen);  
       	zinsen = Double.parseDouble(betragstringt);  
	       for (int j =0; j< playerArray.length; j++) {
	    	   fB = new File("plugins"+System.getProperty("file.separator")+"Bankcraft"+System.getProperty("file.separator")+"Accounts"+System.getProperty("file.separator")+playerArray[j].getName()+".db");
	    	   if ( fB.isFile() == true && fB.equals(file)) {
	    		   playerArray[j].sendMessage((configHandler.interestmsg.replace("%interest", zinsen.toString())).replace("%balance", betrag.toString()));
	    	   }
	       }
	       }
			}
			}
		  }
	       
	       //EXPZinsen
			  File fAt = new File("plugins"+System.getProperty("file.separator")+"Bankcraft"+System.getProperty("file.separator")+"XPAccounts");
		       Player[] playerArrayt = Bankcraft.server.getOnlinePlayers();
			  File[] fileArrays = fAt.listFiles();
			  if (fileArrays != null) {
				for (int it = 0; it < fileArrays.length; it++) {
					if (configHandler.onlinexp == false || Bankcraft.server.getPlayer(fileArrays[it].getName().toString().split(".db")[0]) != null) {
					File filet = fileArrays[it];
			         FileReader frt = new FileReader(filet);
			            BufferedReader readert = new BufferedReader(frt);
			            String stt = readert.readLine(); 
				            	Double betragt = new Integer(stt)*(configHandler.interestxp+1);
			   frt.close();
			   readert.close();
	        writer = new FileWriter(filet);
		       writer.write(betragt.intValue()+System.getProperty("line.separator"));
		       writer.flush();
		       writer.close();
		       if (configHandler.broadcastxp == true) {
			       Double zinsent = new Integer(stt)*(configHandler.interest);
			       for (int j =0; j< playerArrayt.length; j++) {
			    	   fB = new File("plugins"+System.getProperty("file.separator")+"Bankcraft"+System.getProperty("file.separator")+"XPAccounts"+System.getProperty("file.separator")+playerArray[j].getName()+".db");
			    	   if ( fB.isFile() == true && fB.equals(filet)) {
			    		   playerArray[j].sendMessage((configHandler.interestxpmsg.replace("%interest", zinsent.toString())).replace("%balance", betragt.toString()));
			    	   }
			       }
			       }
			}
				}
			}
	  }
 catch (IOException e) {
    e.printStackTrace();
	  }
}
}