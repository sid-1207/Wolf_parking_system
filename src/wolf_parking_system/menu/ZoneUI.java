package wolf_parking_system.menu;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

import java.util.ArrayList;
import wolf_parking_system.crud.ZoneCRUD;
import wolf_parking_system.dbclasses.*;
public class ZoneUI {
    public static void zoneUI(BufferedReader reader) throws NumberFormatException, IOException, SQLException {

        String ZoneID, LotName;

        String[] args;
        boolean exit_val = true;
        String[] main_args = null;
        ZoneCRUD ZoneCRUD= new ZoneCRUD(Main.statement, Main.connection ,Main.result);
        while (exit_val) {

        System.out.println("1. Enter Zone Information");
        System.out.println("2. Update Zone Information");
        System.out.println("3. Delete Zone Information");
        System.out.println("4. View Zone Information");
    

        String input = reader.readLine();

        switch(Integer.parseInt(input)){
            case 1:
                System.out.println("Enter | separated String ZoneID,String LotName");
                args = reader.readLine().split("[|]");
                ZoneID = args[0];
                LotName = args[1];


//                BookCRUD.insertBook(PID, PublicationDate, ISBN, Edition);
                if (ZoneCRUD.enterZoneInfo(ZoneID,LotName)) {
                    System.out.println("Operation Successful");
                } else {
                    System.out.println("Operation Failed");
                }
                return;

            case 2:
                System.out.println("Enter | separated String ZoneID,String LotName");
                args = reader.readLine().split("[|]");
                ZoneID = args[0];
                LotName = args[1];

                if (ZoneCRUD.updateZoneInfo(ZoneID,LotName)) {
                    System.out.println("Operation Successful");
                } else {
                    System.out.println("Operation Failed");
                }
                return;

            case 3:
                System.out.println("Enter | separated String ZoneID,String LotName");
                args = reader.readLine().split("[|]");
                ZoneID = args[0];
                LotName = args[1];

                if (ZoneCRUD.deleteZoneInfo(ZoneID,LotName)) {
                    System.out.println("Operation Successful");
                } else {
                    System.out.println("Operation Failed");
                }
                return;
            case 4:
                // CHECK IF YOU WANT TO DISPLAY ALL (ZONEID,LOTNAME) TUPLES OR ALL ZONEIDS FOR A PARKING LOT
            	ArrayList<Zone> zoneList = ZoneCRUD.viewZone();

            	if (!zoneList.isEmpty()) {
            	    System.out.println("| ZoneID | LotName |");
            	    System.out.println("|--------|---------|");

            	    for (Zone zone : zoneList) {
            	        System.out.printf("| %-6s | %-7s |\n", zone.getZoneID(), zone.getLotName());
            	    }
            	} else {
            	    System.out.println("Table is Empty");
            	}
                return;
            default:
                System.out.println("Enter a valid choice");


        }

        System.out.println("Enter a valid choice");

    }

    }
}