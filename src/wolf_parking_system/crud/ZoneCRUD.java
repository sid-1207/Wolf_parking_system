package wolf_parking_system.crud;

import java.sql.*;
import java.util.ArrayList;

import wolf_parking_system.*;
import wolf_parking_system.dbclasses.Zone;

public class ZoneCRUD {
    public static ArrayList<Zone> viewZone() {
        try {
            Connection conn = DbConnection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("Select * from Zone");
            ArrayList<Zone> list = new ArrayList<>();
            while (rs.next()) {
                Zone p = new Zone(rs.getString("ZoneID"), rs.getString("LotName"));
                list.add(p);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static boolean enterZoneInfo(Connection conn, String ZoneID, String LotName) throws SQLException {
        try {
//            Connection conn = DbConnection.getConnection();
            String query = "INSERT INTO Zone (ZoneID, LotName) VALUES (?,?)";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, ZoneID);
            st.setString(2, LotName);
            st.executeUpdate();

            return true;
        } catch (SQLException ex) {
          //  ex.printStackTrace();
            return false;
        }
    }

    public static Boolean updateZoneInfo(String ZoneID, String LotName) {
        try {
            Connection conn = DbConnection.getConnection();
            String query= "UPDATE Zone SET ZoneID = ? WHERE LotName=?";

            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, ZoneID);
            st.setString(2, LotName);
            st.executeUpdate();
            ResultSet rs = st.executeQuery("Select count(*) as count_val from Zone where ZoneID="+ZoneID);
            int count = 0;
            while (rs.next()) {
                count = rs.getInt("count_val");
            }
            if (count!=0){
                return  true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return Boolean.valueOf(false);
        }
    }

    public static Boolean deleteZoneInfo(String ZoneID, String LotName) {
        try {
            Connection conn = DbConnection.getConnection();
            Statement st = conn.createStatement();
            st.executeUpdate("DELETE FROM Zone WHERE (ZoneID="+ZoneID+" AND LotName="+LotName+")");
            return Boolean.valueOf(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Boolean.valueOf(false);
        }
    }
}
