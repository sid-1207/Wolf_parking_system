package wolf_parking_system.crud;

import java.sql.*;
import java.sql.Driver;
import java.util.*;
import wolf_parking_system.dbclasses.*;


public class DriverCRUD {
    
    public static ArrayList<Driver> viewDriver() {
        try {
            Connection conn = DbConnection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("Select * from Driver");
            ArrayList<Driver> list = new ArrayList<>();
            while (rs.next()) {
                Driver p = new Driver(Long.valueOf(rs.getLong("DriverID")), rs.getString("Name"), rs.getBoolean ("Handicap"), rs.getString("Status"));
                list.add(p);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Boolean insertDriver(Long DriverID, String Name, Boolean Handicap, String Status) {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "insert into Driver (DriverID, Name, Handicap, Status) values (?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, DriverID);
            st.setString(2, Name);
            st.setBoolean(3, Handicap);
            st.setString(4, Status);
            st.executeUpdate();
            ResultSet rs = st.executeQuery(); 
            long driver_id = 0;
            while (rs.next())
                driver_id = rs.getLong("DriverID");
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static Boolean updateDriver(Long DriverID, String Name, Boolean Handicap, String Status) {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "Update Driver set Handicap=?  where DriverID=?, Name=?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setBoolean(1, Handicap);
            st.setLong(2, DriverID);
            st.setString(3, Name);
            // st.setString(4, Status);
            st.executeUpdate();
            ResultSet rs = st.executeQuery("Select count(*) as count_val from Driver where DriverID="+ DriverID + " AND Name =" + Name);
            int count = 0;
            while (rs.next()) {
                count = rs.getInt("count_val");

            }
            if (count!=0){
                return  true;
            }
            return false;
            //return Boolean.valueOf(true);
        } catch (SQLException e) {
            e.printStackTrace();
            return Boolean.valueOf(false);
        }
    }

    public static Boolean deleteArticle(Long DriverID) {
        try {
            Connection conn = DbConnection.getConnection();
            String query = "DELETE FROM Driver WHERE DriverID=?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, DriverID);
            st.executeUpdate();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return Boolean.valueOf(false);
        }
    }
}
