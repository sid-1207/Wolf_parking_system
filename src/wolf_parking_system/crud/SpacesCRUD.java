package wolf_parking_system.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.ArrayList;
import wolf_parking_system.dbclasses.Spaces;
import wolf_parking_system.dbclasses.Zone;
import wolf_parking_system.connection.*;

public class SpacesCRUD {

    public static ArrayList<Spaces> viewSpaces() {
        try {
            Connection conn = conn.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Spaces;");
            ArrayList<Spaces> list = new ArrayList<>();
            while (rs.next()) {
                Spaces p = new Spaces(String.valueOf(rs.getString("ZoneID")), rs.getString("LotName"), rs.getInt("SpaceNumber"), rs.getString("SpaceType"),rs.getBoolean("Availability"));
                list.add(p);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

        public static boolean addSpace(String ZoneID, String LotName, Integer SpaceNumber, String SpaceType, Boolean Availability) {
            boolean state = false;
            try {
                Connection conn = conn.getConnection();
                String query = "insert into Spaces(ZoneID, LotName, SpaceNumber, SpaceType, Availability) values (?,?,?,?,?)";
                PreparedStatement st = conn.prepareStatement(query);
                st.setString(1, ZoneID);
                st.setString(2, LotName);
                st.setInt(3, SpaceNumber);
                st.setString(4, SpaceType);
                st.setBoolean(5, Availability);
                st.executeUpdate();
                return true;
    
            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        }

        public static Boolean updateSpace(String ZoneID, String LotName, Integer SpaceNumber, String SpaceType, Boolean Availability) {
            try {
                Connection conn = conn.getConnection();
                String query = "UPDATE Spaces SET SpaceType=?, Availability=? WHERE (ZoneID ="+ZoneID+ " AND LotName ="+ LotName+ "AND Space Number = " + SpaceNumber + ")";
                PreparedStatement st = conn.prepareStatement(query);
                st.setString(4, SpaceType);
                st.setBoolean(5, Availability);
                st.executeUpdate();
                return Boolean.valueOf(true);
            } catch (SQLException e) {
                e.printStackTrace();
                return Boolean.valueOf(false);
            }
        }

        public static Boolean deleteSpace(String ZoneID, String LotName, Integer SpaceNumber) {
            try {
                Connection conn = conn.getConnection();
                Statement st = conn.createStatement();
                st.executeUpdate("DELETE FROM Spaces WHERE (ZoneID=" + ZoneID + " AND LotName = " + LotName + " AND Space Number = " + SpaceNumber + ")");
                return Boolean.valueOf(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
                return Boolean.valueOf(false);
            }
        }
}