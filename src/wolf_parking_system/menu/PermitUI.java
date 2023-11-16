
package wolf_parking_system.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Date;
import java.util.*;
import wolf_parking_system.crud.PermitCRUD;
import java.text.SimpleDateFormat;

import wolf_parking_system.dbclasses.Permit;
public class PermitUI {
    public static void permitUI(BufferedReader reader) throws NumberFormatException, IOException, SQLException {
        String PermitID;
        String PermitType;
        Time ExpirationTime;
        java.sql.Date StartDate;
        java.sql.Date EndDate;

        String[] main_args = null;
        String [] args = null;
        boolean exit_val = true;
        PermitCRUD PermitCRUD= new PermitCRUD(Main.statement, Main.connection ,Main.result);

        while (exit_val) {
            System.out.println("Permit operations");
            System.out.println("1. Add Permit Info");
            System.out.println("2. Update Permit Info");
            System.out.println("3. Delete Permit Info");
            System.out.println("4. View Permit Info");
            System.out.println("5. Back to Main Menu");

            String input = reader.readLine();

            switch (Integer.parseInt(input)) {
                case 1:
                	System.out.println("Enter | separated String PermitID, String PermitType, Time ExpirationTime, Date StartDate, Date EndDate");
                	args = reader.readLine().split("[|]");

                	PermitID = args[0];
                	PermitType = args[1];

                	 try {
                   	    ExpirationTime = Time.valueOf(args[2]); // Assuming Time is in HH:mm:ss format
                  
                   	    StartDate = Date.valueOf(args[3]);
                   	    EndDate = Date.valueOf(args[4]);
                   	} catch (IllegalArgumentException e) {
                   	    System.out.println("Invalid date or time format. Please enter dates in YYYY-MM-DD format and time in HH:mm:ss format.");
                   	    // Handle the exception or ask the user to enter the correct format
                   	    return;
                   	}
   
                  if (PermitCRUD.enterPermitInfo(PermitID, PermitType, ExpirationTime, StartDate, EndDate)) {
                    System.out.println("Operation Successful");
                  } else {
                        System.out.println("Operation Failed");
                  }
                  break;
                case 2:
                  System.out.println("Enter | separated String PermitID, String PermitType, Time ExpirationTime, Date StartDate, Date EndDate");
                  args = reader.readLine().split("[|]");
                  PermitID = String.valueOf(args[0]);
                  PermitType = args[1];
                  try {
              	    ExpirationTime = Time.valueOf(args[2]); // Assuming Time is in HH:mm:ss format
              	    StartDate = Date.valueOf(args[3]);
              	    EndDate = Date.valueOf(args[4]);
              	} catch (IllegalArgumentException e) {
              	    System.out.println("Invalid date or time format. Please enter dates in YYYY-MM-DD format and time in HH:mm:ss format.");
              	    // Handle the exception or ask the user to enter the correct format
              	    return;
              	}
                  if (PermitCRUD.updatePermitInfo(PermitID, PermitType, ExpirationTime, StartDate, EndDate)) {
                    System.out.println("Operation Successful");
                  } else {
                        System.out.println("Operation Failed");
                  }
                  break;
                case 3:
                  System.out.println("Enter | separated String PermitID");
                  args = reader.readLine().split("[|]");
                  PermitID = String.valueOf(args[0]);
                
                  if (PermitCRUD.deletePermitInfo(PermitID)) {
                    System.out.println("Operation Successful");
                  } else {
                        System.out.println("Operation Failed");
                  }
                  break;

                case 4:
                	ArrayList<Permit> permitList = PermitCRUD.viewPermit();

                	if (!permitList.isEmpty()) {
                	    System.out.println("| PermitID | PermitType | ExpirationTime | StartDate | EndDate");
                	    System.out.println("|--------|---------|---------|---------|---------|");

                	    for (Permit permit : permitList) {
                	    	System.out.printf("| %-6s | %-7s | %-12s | %-10s | %-10s |\n", 
                	                  permit.getPermitID(), 
                	                  permit.getPermitType(),
                	                  permit.getExpirationTime(),
                	                  permit.getStartDate(),
                	                  permit.getEndDate());
   }
                	} else {
                	    System.out.println("Table is Empty");
                	}
                    return;
                case 5:
                    exit_val = false;
                    break;
                default:
                  System.out.println("Enter a valid choice");
                  break;
            
            }

        }
    }
}