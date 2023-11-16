package wolf_parking_system.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

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
        String [] args = null;
        boolean exit_val = true;
        DriverCRUD DriverCRUD= new DriverCRUD(Main.statement, Main.connection ,Main.result);

        while (exit_val) {
            System.out.println("Driver operations");
            System.out.println("1. Add Driver Info");
            System.out.println("2. Update Driver Info");
            System.out.println("3. Delete Driver Info");
            System.out.println("4. View Driver Info");
            System.out.println("5. Back to Main Menu");

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
                  System.out.println("Enter | separated Long DriverID, String Name, Boolean Handicap, String Status");
                  args = reader.readLine().split("[|]");
                  DriverID = Long.valueOf(args[0]);
                  Name = args[1];
                  Handicap = Boolean.valueOf(args[2]);
                  Status = args[3];

                  if (DriverCRUD.updateDriverInfo(DriverID, Name, Handicap, Status)) {
                    System.out.println("Operation Successful");
                  } else {
                        System.out.println("Operation Failed");
                  }
                  break;
                case 3:
                  System.out.println("Enter | separated Long DriverID, String Name, Boolean Handicap, String Status");
                  args = reader.readLine().split("[|]");
                  DriverID = Long.valueOf(args[0]);
                  Name = args[1];
                  Handicap = Boolean.valueOf(args[2]);
                  Status = args[3];

                  if (DriverCRUD.deleteDriverInfo(DriverID)) {
                    System.out.println("Operation Successful");
                  } else {
                        System.out.println("Operation Failed");
                  }
                  break;

                case 4:
                  if (!DriverCRUD.viewDriver().isEmpty()) {
                    System.out.println("Operation Successful");
                  } else {
                    System.out.println("Operation Failed");
                  } 

                default:
                  System.out.println("Enter a valid choice");
                  break;
            
            }

        }
    }
}
