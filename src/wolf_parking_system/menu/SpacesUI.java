package wolf_parking_system.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.time.ZoneId;

import wolf_parking_system.crud.DriverCRUD;
import wolf_parking_system.crud.SpacesCRUD;
import wolf_parking_system.dbclasses.Spaces;
import java.util.*;

public class SpacesUI {

    public static String buildQueryUpdate(BufferedReader reader) throws IOException {
        // Initialize Scanner for user input

        // Create StringBuilder for building the SQL query
        StringBuilder queryBuilder = new StringBuilder("UPDATE Driver SET ");

        // Columns for the SET clause
        String[] setColumns = { "SpaceType", "Availability" };

        for (String column : setColumns) {
            System.out.print("Enter value for " + column + ": ");
            String value = reader.readLine().trim();

            if (!value.isEmpty()) {
                queryBuilder.append(column).append(" = ").append(value).append(", ");
            }
        }

        // Remove the trailing comma and space from the SET clause
        if (queryBuilder.charAt(queryBuilder.length() - 2) == ',') {
            queryBuilder.delete(queryBuilder.length() - 2, queryBuilder.length());
        }

        // Add WHERE clause if there are values for the WHERE condition
        if (queryBuilder.toString().contains(" = ")) {
            queryBuilder.append(" WHERE ");

            // Columns for the WHERE clause
            String[] whereColumns = { "ZoneID", "LotName", "SpaceNumber", "SpaceType", "Availability" };

            // Get values for the WHERE clause
            for (String column : whereColumns) {
                System.out.print("WHERE " + column + " =: ");
                String value = reader.readLine().trim();

                if (!value.isEmpty()) {
                    queryBuilder.append(column).append(" = ").append(value).append(" AND ");
                }
            }

            // Remove the trailing "AND" from the WHERE clause
            if (queryBuilder.toString().endsWith("AND ")) {
                queryBuilder.delete(queryBuilder.length() - 4, queryBuilder.length());
            }
        }
        System.out.println(queryBuilder.toString());

        return queryBuilder.toString();
    }

    public static void spacesUI(BufferedReader reader) throws NumberFormatException, IOException, SQLException {

        String[] args;
        boolean exit_val = true;
        String[] main_args = null;
        SpacesCRUD spaces = new SpacesCRUD(Main.statement, Main.connection, Main.result);

        while (exit_val) {

            String ZoneID, LotName, SpaceType;
            int SpaceNumber;
            Boolean Availability;

            System.out.println("1. Insert New Space");
            System.out.println("2. Update Space");
            System.out.println("3. Delete Space");
            System.out.println("4. View Space Information");
            System.out.println("5. Custom Update Space");
            System.out.println("6. Back to Main Menu");

            System.out.print("Enter your Choice:");

            String input = reader.readLine();

            switch (Integer.parseInt(input)) {
                case 1:
                    System.out.println(
                            "Enter | separated String ZoneID, String LotName, Integer SpaceNumber, String SpaceType, Boolean Availability");
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
                    System.out.println(
                            "Enter | separated String ZoneID, String LotName, Integer SpaceNumber, String SpaceType, Boolean Availability");
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
                    ArrayList<Spaces> spacesList = spaces.viewSpaces();

                    if (!spacesList.isEmpty()) {
                        System.out.println("| ZoneID  | LotName | SpaceNumber | SpaceType          | Availability |");
                        System.out.println("|---------|---------|-------------|--------------------|---------------|");

                        for (Spaces space : spacesList) {
                            System.out.printf("| %-7s | %-7s | %-11s | %-18s | %-13s |\n",
                                    space.getZoneID(), space.getLotName(), space.getSpaceNumber(), space.getSpaceType(),
                                    space.getAvailability());
                        }
                    } else {
                        System.out.println("Table is Empty");
                    }
                    return;

                case 5:
                    String query = buildQueryUpdate(reader);

                    if (spaces.updateSpaces(query)) {
                        System.out.println("Operation Successful");
                    } else {
                        System.out.println("Operation Failed");
                    }
                    break;

                case 6:
                    exit_val = false;
                    break;

                // case 6:
                // System.out.println("Enter | separated String ZoneID, String LotName, Integer
                // SpaceNumber");
                // args = reader.readLine().split("[|]");
                // ZoneID = args[0];
                // LotName = args[1];
                // SpaceNumber = Integer.parseInt(args[2]);
                //
                // // Gather conditions for the WHERE clause
                // Map<String, Object> conditions = new HashMap<>();
                // conditions.put("ZoneID", ZoneID);
                // conditions.put("LotName", LotName);
                // conditions.put("SpaceNumber", SpaceNumber);
                //
                // // Call the updateSpaces method
                // if (spaces.updatecustomizedSpaces("NewSpaceType", true, conditions)) {
                // System.out.println("Update Successful");
                // } else {
                // System.out.println("Update Failed");
                // }

                default:
                    System.out.println("Enter a valid choice");
            }
        }
    }
}