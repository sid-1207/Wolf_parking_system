package wolf_parking_system.menu;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class Main {

    public static void main(String[] args) {

        while (true) {
            System.out.println("WOLFPUB DB");
            System.out.println("Menu:");
            System.out.println("1. Driver");
            System.out.println("2. Vehicle");
            System.out.println("3. Citation");
            System.out.println("4. ParkingLot");
            System.out.println("5. Permit");
            System.out.println("6. Zones");
            System.out.println("7. Spaces");
            System.out.println("8. EXIT");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String choice = reader.readLine();
                switch (Integer.parseInt(choice)) {
                    case 1:
                        DriverUI.driverUI(reader);
                        break;
                    case 2:
                        VehicleUI.vehicleUI(reader);
                        break;
                    case 3:
                        CitationUI.citationUI(reader);
                        break;
                    case 5:
                        ParkingLotUI.parkinglotUI(reader);
                        break;
                    case 6:
                        ZoneUI.zoneUI(reader);
                        break;
                    case 7:
                        SpacesUI.spacesUI(reader);
                        break;
                    case 8:
                        System.exit(0);
                        break;
                }
                System.out.println("Please enter a valid choice");
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }

        }
    }
}




