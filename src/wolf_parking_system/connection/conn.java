package wolf_parking_system.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class conn {
        public static Connection getConnection() throws SQLException
        {
        	Connection conn = null;
        	String url = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/";
        	String user = "YourUnityId";
        	String pswd = "YourStudentId";

            try {
                Class.forName("org.mariadb.jdbc.Driver");
                conn = DriverManager.getConnection(url, user, pswd);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            //System.out.println("CONN: " + connection);
            return conn;
        }
        		
        }
