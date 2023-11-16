package wolf_parking_system.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import wolf_parking_system.dbclasses.*;
import java.util.*;
import wolf_parking_system.crud.CitationCRUD;
import wolf_parking_system.crud.VehicleCRUD;

public class VehicleUI {
    public static void vehicleUI(BufferedReader reader) throws NumberFormatException, IOException, SQLException {
        String CarLicenseNumber;
        String Model;
        Integer Year;
        String Manufacturer;
        String Color;
        Long DriverID;

        String[] args;
        boolean exit_val = true;
        String[] main_args = null;
        VehicleCRUD VehicleCRUD = new VehicleCRUD(Main.statement, Main.connection, Main.result);
        while (exit_val) {

            System.out.println("1. Enter Vehicle Information");
            System.out.println("2. Update Vehicle Information");
            System.out.println("3. Delete Vehicle Information");
            System.out.println("4. View Vehicle Information");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your Choice:");

            String input = reader.readLine();

            switch (Integer.parseInt(input)) {
                case 1:
                    System.out.println(
                            "Enter | separated String CarLicenseNumber,String Model,Integer Year,String Manufacturer,String Color,Long DriverID");
                    args = reader.readLine().split("[|]");
                    CarLicenseNumber = args[0];
                    Model = args[1];
                    Year = Integer.valueOf(args[2]);
                    Manufacturer = args[3];
                    Color = args[4];
                    DriverID = Long.valueOf(args[5]);

                    if (VehicleCRUD.AddVehicle(CarLicenseNumber, Model, Year, Manufacturer, Color, DriverID)) {
                        System.out.println("Operation Successful");
                    } else {
                        System.out.println("Operation Failed");
                    }
                    return;

                case 2:
                    System.out.println(
                            "Enter | separated String CarLicenseNumber,String Model,Integer Year,String Manufacturer,String Color,Long DriverID");
                    args = reader.readLine().split("[|]");
                    CarLicenseNumber = args[0];
                    Model = args[1];
                    Year = Integer.valueOf(args[2]);
                    Manufacturer = args[3];
                    Color = args[4];
                    DriverID = Long.valueOf(args[5]);
                    if (VehicleCRUD.updateVehicle(CarLicenseNumber, Model, Year, Manufacturer, Color, DriverID)) {
                        System.out.println("Operation Successful");
                    } else {
                        System.out.println("Operation Failed");
                    }
                    return;

                case 3:
                    System.out.println("Enter | separated CarLicenseNumber");
                    args = reader.readLine().split("[|]");
                    CarLicenseNumber = args[0];

                    if (VehicleCRUD.deleteVehicle(CarLicenseNumber)) {
                        System.out.println("Operation Successful");
                    } else {
                        System.out.println("Operation Failed");
                    }
                    return;
                case 4:

                	ArrayList<Vehicle> vehicleList = VehicleCRUD.viewVehicles();

                            	if (!vehicleList.isEmpty()) {
                            	    System.out.println("| CarLicenseNumber | Model | Year | Manufacturer | Color | DriverID |");
                            	    System.out.println("|-------------------|-------|------|--------------|-------|----------|");

                            	    for (Vehicle vehicle : vehicleList) {
                            	        System.out.printf("| %-17s | %-5s | %-4s | %-12s | %-5s | %-8s |\n",
                            	                vehicle.getCarLicenseNumber(), vehicle.getModel(), vehicle.getYear(),
                            	                vehicle.getManufacturer(), vehicle.getColor(), vehicle.getDriverID());
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
