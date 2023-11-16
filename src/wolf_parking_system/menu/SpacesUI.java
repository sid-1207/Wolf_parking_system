package wolf_parking_system.menu;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.time.ZoneId;

import wolf_parking_system.crud.SpacesCRUD;
import wolf_parking_system.dbclasses.Spaces;

public class SpacesUI {
     public static void spacesUI(BufferedReader reader) throws NumberFormatException, IOException, SQLException {

          String[] args;
          boolean exit_val = true;
          String[] main_args = null;
          SpacesCRUD spaces= new SpacesCRUD(Main.statement, Main.connection ,Main.result);
          
          while (exit_val) {

          String ZoneID,LotName, SpaceType;
          int SpaceNumber;
          Boolean Availability;

          System.out.println("1. Insert New Space");
          System.out.println("2. Update Space");
          System.out.println("3. Delete Space");
          System.out.println("4. View Space Information");
          
          String input = reader.readLine();

        switch(Integer.parseInt(input)){
            case 1:
                System.out.println("Enter | separated String ZoneID, String LotName, Integer SpaceNumber, String SpaceType, Boolean Availability");
                args = reader.readLine().split("[|]");
                ZoneID = args[0];
                LotName = args[1];
                SpaceNumber = Integer.parseInt(args[2]);
                SpaceType = args[3];
                Availability = Boolean.parseBoolean(args[4]);

                if (spaces.AddSpace(ZoneID, LotName, SpaceNumber, SpaceType, Availability)) {
                    System.out.println("Operation Successful");
                } else {
                    System.out.println("Operation Failed");
                }
                return;

            case 2:
                System.out.println("Enter | separated String ZoneID, String LotName, Integer SpaceNumber, String SpaceType, Boolean Availability");
                args = reader.readLine().split("[|]");
                ZoneID = args[0];
                LotName = args[1];
                SpaceNumber = Integer.parseInt(args[2]);
                SpaceType = args[3];
                Availability = Boolean.parseBoolean(args[4]);

                if (spaces.updateSpaces(ZoneID, LotName, SpaceNumber, SpaceType, Availability)) {
                    System.out.println("Operation Successful");
                } else {
                    System.out.println("Operation Failed");
                }
                return;

            case 3:
                System.out.println("Enter | separated String ZoneID, String LotName, Integer SpaceNumber");
                args = reader.readLine().split("[|]");
                ZoneID = args[0];
                LotName = args[1];
                SpaceNumber = Integer.parseInt(args[2]);

                if (spaces.deleteSpace(ZoneID, LotName, SpaceNumber)) {
                    System.out.println("Operation Successful");
                } else {
                    System.out.println("Operation Failed");
                }
                return;

            case 4:
                if (!spaces.viewSpaces().isEmpty()) {
                    System.out.println("Operation Successful");
                } else {
                    System.out.println("Operation Failed");
                }
                return;
          case 5:
                exit_val= false;
                break;

            default:
                System.out.println("Enter a valid choice");
             }
          }
     }
}