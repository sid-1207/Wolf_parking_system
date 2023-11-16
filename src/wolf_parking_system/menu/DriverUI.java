package wolf_parking_system.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

public class DriverUI {
      public static void driverUI(BufferedReader reader) throws NumberFormatException, IOException, SQLException {
            String[] main_args = null;
            boolean exit_val = true;
            while (exit_val) {
                  System.out.println("Driver operations");
                  System.out.println("1. Add Driver Info");
                  System.out.println("2. Update Driver Info");
                  System.out.println("3. Delete Driver Info");
                  System.out.println("4. View Driver Info");
                  //System.out.println("5. Staff Payment Operations");
                  //System.out.println("6. Reports");
                  System.out.println("7. Back to Main Menu");

                  String input = reader.readLine();
                  String[] str = null;

                  switch (Integer.parseInt(input)) {
                  case 1:
                        PublicationUI.publicationUI(reader);
                        break;
                  case 2:
                        StaffUI.staffUI(reader);
                        break;
                  case 3:
                        DistributorOperations.distributorOperationsUI(reader);
                        break;
                  case 4:
                        DistributorUI.distributorUI(reader);
                        break;
                  case 5:
                        PaymentUI.paymentUI(reader);
                        break;
                  case 6:
                        ReportUI.reportUI(reader);
                        break;
                  case 7:
                        Main.main(str);
                  default:
                        System.out.println("Enter a valid choice");
                  }

            }
      }
}
