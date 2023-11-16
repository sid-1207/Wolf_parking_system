package wolf_parking_system.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import wolf_parking_system.dbclasses.*;
import wolf_parking_system.crud.CitationCRUD;
import wolf_parking_system.crud.DriverCRUD;
import wolf_parking_system.crud.VehicleCRUD;
import wolf_parking_system.crud.ZoneCRUD;
import wolf_parking_system.crud.DriverCRUD;

public class DriverUI {
  public static void driverUI(BufferedReader reader) throws NumberFormatException, IOException, SQLException {
    Long DriverID;
    String Name, Status;
    Boolean Handicap;

    String[] main_args = null;
    String[] args = null;
    boolean exit_val = true;
    DriverCRUD DriverCRUD = new DriverCRUD(Main.statement, Main.connection, Main.result);

    while (exit_val) {
      System.out.println("Driver operations");
      System.out.println("1. Add Driver Info");
      System.out.println("2. Update Driver Info");
      System.out.println("3. Delete Driver Info");
      System.out.println("4. View Driver Info");
      System.out.println("5. Back to Main Menu");

      System.out.print("Enter your Choice:");

      String input = reader.readLine();

      switch (Integer.parseInt(input)) {
        case 1:
          System.out.println("Enter | separated Long DriverID, String Name, Boolean Handicap, String Status");
          args = reader.readLine().split("[|]");
          DriverID = Long.valueOf(args[0]);
          Name = args[1];
          Handicap = Boolean.valueOf(args[2]);
          Status = args[3];

                  if (DriverCRUD.enterDriverInfo(DriverID, Name, Handicap, Status)) {
                    System.out.println("Operation Successful");
                  } else {
                        System.out.println("Operation Failed");
                  }
                  break;
                case 2:
                  System.out.println("Enter | separated Boolean Handicap,String Name,Long DriverID");
                  args = reader.readLine().split("[|]");
                  Handicap = Boolean.valueOf(args[0]);
                  Name = args[1];
                  DriverID = Long.valueOf(args[2]);
                  
                 
                  

                  if (DriverCRUD.updateDriverInfo(Handicap,Name,DriverID)) {
                    System.out.println("Operation Successful");
                  } else {
                        System.out.println("Operation Failed");
                  }
                  break;
                case 3:
                  System.out.println("Enter DriverID");
                  args = reader.readLine().split("[|]");
                  DriverID = Long.valueOf(args[0]);


          if (DriverCRUD.deleteDriverInfo(DriverID)) {
            System.out.println("Operation Successful");
          } else {
            System.out.println("Operation Failed");
          }
          break;

        case 4:

ArrayList<Driver> driverList = DriverCRUD.viewDriver();

                	if (! driverList.isEmpty()) {
                		System.out.println("| DriverID | Name | Handicap | Status |");
                		System.out.println("|--------|---------|---------|---------|");
               	    for (Driver driver : driverList) {
               	    	System.out.printf("| %-9s | %-20s | %-8s | %-10s |\n", driver.getDriverID(), driver.getName(), driver.getHandicap(), driver.getStatus());

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
