package wolf_parking_system.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

import wolf_parking_system.crud.CitationCRUD;
import wolf_parking_system.crud.PermitCRUD;

public class PermitUI {
    public static void permitUI(BufferedReader reader) throws NumberFormatException, IOException, SQLException {
        String PermitID;
        String PermitType, ExpirationTime, StartDate, EndDate;

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
                  System.out.println("Enter | separated String PermitID, String PermitType, String ExpirationTime, String StartDate, String EndDate");
                  args = reader.readLine().split("[|]");
                  PermitID = args[0];
                  PermitType = args[1];
                  ExpirationTime = args[2];
                  StartDate = args[3];
                  EndDate = args[4];

                  if (PermitCRUD.enterPermitInfo(PermitID, PermitType, ExpirationTime, StartDate, EndDate)) {
                    System.out.println("Operation Successful");
                  } else {
                        System.out.println("Operation Failed");
                  }
                  break;
                case 2:
                  System.out.println("Enter | separated String PermitID, String PermitType, String ExpirationTime, String StartDate, String EndDate");
                  args = reader.readLine().split("[|]");
                  PermitID = String.valueOf(args[0]);
                  PermitType = args[1];
                  ExpirationTime = String.valueOf(args[2]);
                  StartDate = args[3];

                  if (PermitCRUD.updatePermitInfo(PermitID, PermitType, ExpirationTime, StartDate, EndDate)) {
                    System.out.println("Operation Successful");
                  } else {
                        System.out.println("Operation Failed");
                  }
                  break;
                case 3:
                  System.out.println("Enter | separated String PermitID, String PermitType, String ExpirationTime, String StartDate");
                  args = reader.readLine().split("[|]");
                  PermitID = String.valueOf(args[0]);
                  PermitType = args[1];
                  ExpirationTime = String.valueOf(args[2]);
                  StartDate = args[3];
                  EndDate = args[4];

                  if (PermitCRUD.deletePermitInfo(PermitID)) {
                    System.out.println("Operation Successful");
                  } else {
                        System.out.println("Operation Failed");
                  }
                  break;

                case 4:
                  if (!PermitCRUD.viewPermit().isEmpty()) {
                    System.out.println("Operation Successful");
                  } else {
                    System.out.println("Operation Failed");
                  } 
                  break;

                default:
                  System.out.println("Enter a valid choice");
                  break;
            
            }

        }
    }
}
