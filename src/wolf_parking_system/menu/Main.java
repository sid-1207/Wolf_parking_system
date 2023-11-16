package wolf_parking_system.menu;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class Main {
	static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/rlobo";
	// Put your oracle ID and password here

	protected static Connection connection = null;
	protected static Statement statement = null;
	protected static ResultSet result = null;
    public static void main(String[] args) {
     initialize();
        boolean x = true;
        while (x) {
            System.out.println("WOLFParking DB");
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
                    case 4:
                        ParkingLotUI.parkinglotUI(reader);
                        break;
                    case 5:
                        PermitUI.permitUI(reader);
                        break;
                    case 6:
                        ZoneUI.zoneUI(reader);
                        break;
                    case 7:
                        SpacesUI.spacesUI(reader);
                        break;
                    case 8:
                        x = false;
                        break;
                }
                System.out.println("Please enter a valid choice");
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }

        }
   	close();
   	System.out.println("connection closed");
    }




private static void connectToDatabase() throws ClassNotFoundException, SQLException {
    Class.forName("org.mariadb.jdbc.Driver");

    String user = "rlobo";
    String password = "200537366";

    connection = DriverManager.getConnection(jdbcURL, user, password);
    statement = connection.createStatement();
    }

private static void initialize() {
		try {
			connectToDatabase();
        }
        catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
    }


private static void close() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (result != null) {
			try {
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
