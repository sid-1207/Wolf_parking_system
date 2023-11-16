package wolf_parking_system.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import wolf_parking_system.dbclasses.*;
import wolf_parking_system.crud.CitationCRUD;
import wolf_parking_system.crud.DriverCRUD;
import wolf_parking_system.crud.ZoneCRUD;
import java.util.*;

public class CitationUI {

    public static String buildQueryUpdate(BufferedReader reader) throws IOException {
        // Initialize Scanner for user input

        // Create StringBuilder for building the SQL query
        StringBuilder queryBuilder = new StringBuilder("UPDATE Citation1 SET ");

        // Columns for the SET clause
        String[] setColumns = { "PaymentStatus", "AppealStatus", "CitationDate", "CitationTime", "LotName", "Category",
                "Fee" };

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
            String[] whereColumns = { "CitationNumber", "PaymentStatus", "AppealStatus", "CitationDate", "CitationTime",
                    "LotName", "Category", "Fee" };

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

    public static void citationUI(BufferedReader reader) throws NumberFormatException, IOException, SQLException {
        String CitationNumber;
        String PaymentStatus;
        Boolean AppealStatus;
        Date CitationDate;
        Time CitationTime;
        String LotName;
        String Category;

        String[] args;
        boolean exit_val = true;
        String[] main_args = null;
        CitationCRUD CitationCRUD = new CitationCRUD(Main.statement, Main.connection, Main.result);
        while (exit_val) {

            System.out.println("1. Enter Citation Information");
            System.out.println("2. Update Citation Information");
            System.out.println("3. Delete Citation Information");
            System.out.println("4. View Citation Information");
            System.out.println("5. Back to Main Menu");

            System.out.print("Enter your Choice:");

            String input = reader.readLine();

            switch (Integer.parseInt(input)) {
                case 1:
                    System.out.println(
                            "Enter | separated String CitationNumber,String PaymentStatus,Boolean AppealStatus,Date CitationDate,Time CitationTime, String LotName, String Category");
                    args = reader.readLine().split("[|]");
                    CitationNumber = args[0];
                    PaymentStatus = args[1];
                    AppealStatus = Boolean.valueOf(args[2]);
                    CitationDate = Date.valueOf(args[3]);
                    CitationTime = Time.valueOf(args[4]);
                    LotName = args[5];
                    Category = args[6];

                    if (CitationCRUD.addCitation(CitationNumber, PaymentStatus, AppealStatus, CitationDate,
                            CitationTime, LotName, Category)) {
                        System.out.println("Operation Successful");
                    } else {
                        System.out.println("Operation Failed");
                    }
                    return;

                case 2:
                    System.out.println(
                            "Enter | separated String CitationNumber,String PaymentStatus,Boolean AppealStatus,Date CitationDate,Time CitationTime, String LotName, String Category");
                    args = reader.readLine().split("[|]");
                    CitationNumber = args[0];
                    PaymentStatus = args[1];
                    AppealStatus = Boolean.valueOf(args[2]);
                    CitationDate = Date.valueOf(args[3]);
                    CitationTime = Time.valueOf(args[4]);
                    LotName = args[5];
                    Category = args[6];

                    if (CitationCRUD.updateCitation(CitationNumber, PaymentStatus, AppealStatus, CitationDate,
                            CitationTime, LotName, Category)) {
                        System.out.println("Operation Successful");
                    } else {
                        System.out.println("Operation Failed");
                    }
                    return;

                case 3:
                    // CODE FOR DELETE CITATION
                    System.out.println("Enter | separated String CitationNumber");
                    args = reader.readLine().split("[|]");
                    CitationNumber = args[0];

                    if (CitationCRUD.deleteCitation(CitationNumber)) {
                        System.out.println("Operation Successful");
                    } else {
                        System.out.println("Operation Failed");
                    }
                    break;
                case 4:
                    ArrayList<Citation1> citationList = CitationCRUD.getCitation();

                    if (!citationList.isEmpty()) {
                        // CitationNumber, PaymentStatus, AppealStatus, CitationDate, CitationTime,
                        // LotName, Category

                        System.out.println(
                                "| CitationNumber | PaymentStatus | AppealStatus | CitationDate | CitationTime | LotName | Category |Fee|");
                        System.out.println("|--------|---------|---------|---------|---------|---------|---------|");
                        for (Citation1 citation : citationList) {
                            int fee = CitationCRUD.getFeeByCategory(citation.Category);
                            System.out.printf("| %-20s | %-15s | %-13b | %-15s | %-15s | %-20s | %-20s|%-3d |\n",
                                    citation.getCitationNumber(),
                                    citation.getPaymentStatus(),
                                    citation.getAppealStatus(),
                                    citation.getCitationDate(),
                                    citation.getCitationTime(),
                                    citation.getLotName(),
                                    citation.getCategory(), fee);
                        }
                    } else {
                        System.out.println("Table is Empty");
                    }
                    return;
                case 5:
                    exit_val = false;
                    break;

                case 6:
                    String query = buildQueryUpdate(reader);

                    if (CitationCRUD.updateCitation(query)) {
                        System.out.println("Operation Successful");
                    } else {
                        System.out.println("Operation Failed");
                    }
                    break;

                default:
                    System.out.println("Enter a valid choice");

            }

            System.out.println("Enter a valid choice");

        }
    }
}
