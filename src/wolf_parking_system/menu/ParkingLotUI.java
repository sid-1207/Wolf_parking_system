package wolf_parking_system.menu;

import java.beans.Statement;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import wolf_parking_system.crud.DriverCRUD;
import wolf_parking_system.crud.ParkingLotCRUD;

import wolf_parking_system.dbclasses.ParkingLot;

public class ParkingLotUI {

    public static void parkinglotUI(BufferedReader reader) throws NumberFormatException, IOException, SQLException {
        String LotName, Address;

        String[] args;
        boolean exit_val = true;
        String[] main_args = null;
        ParkingLotCRUD parkinglot = new ParkingLotCRUD(Main.statement, Main.connection, Main.result);
        while (exit_val) {

            System.out.println("1. Insert ParkingLot Information");
            System.out.println("2. Update ParkingLot Information");
            System.out.println("3. Delete ParkingLot Information");
            System.out.println("4. View ParkingLot Information");
            System.out.println("6. Back to Main Menu");

            System.out.print("Enter your Choice:");

            String input = reader.readLine();

            switch (Integer.parseInt(input)) {
                case 1:
                    System.out.println("Enter | separated String LotName,String Address");
                    args = reader.readLine().split("[|]");
                    LotName = args[0];
                    Address = args[1];

                    // BookCRUD.insertBook(PID, PublicationDate, ISBN, Edition);
                    if (parkinglot.insertParkinglot(LotName, Address)) {
                        System.out.println("Operation Successful");
                    } else {
                        System.out.println("Operation Failed");
                    }
                    return;

                case 2:
                    System.out.println("Enter | separated String LotName,String Address");
                    args = reader.readLine().split("[|]");
                    LotName = args[0];
                    Address = args[1];

                    if (parkinglot.updateParkingLot(LotName, Address)) {
                        System.out.println("Operation Successful");
                    } else {
                        System.out.println("Operation Failed");
                    }
                    return;

                case 3:
                    System.out.println("Enter | separated String LotName");
                    args = reader.readLine().split("[|]");
                    LotName = args[0];

                    if (parkinglot.deleteParkingLot(LotName)) {
                        System.out.println("Operation Successful");
                    } else {
                        System.out.println("Operation Failed");
                    }
                    return;
                case 4:
                    // CHECK IF YOU WANT TO DISPLAY ALL (ZONEID,LOTNAME) TUPLES OR ALL ZONEIDS FOR A
                    // PARKING LOT
                    ArrayList<ParkingLot> parkingLotList = parkinglot.viewParkingLots();

                    if (!parkingLotList.isEmpty()) {
                        System.out.println("| LotName | Address |");
                        System.out.println("|--------|---------|");
                        for (ParkingLot parkingLot : parkingLotList) {
                            System.out.printf("| %-9s | %-20s |\n", parkingLot.getLotName(), parkingLot.getAddress());

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

            }

            System.out.println("Enter a valid choice");

        }
    }
}
