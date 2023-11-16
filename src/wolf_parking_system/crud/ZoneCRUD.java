package wolf_parking_system.crud;

import java.sql.*;
import java.util.ArrayList;

import wolf_parking_system.*;
import wolf_parking_system.dbclasses.Zone;

public class ZoneCRUD {
    private  Statement statement;
    private Connection connection;
    private ResultSet result;
    public ZoneCRUD(Statement statement,Connection connection,ResultSet result){
        this.statement=statement;
        this.connection=connection;
        this.result=result;
}
    public  ArrayList<Zone> viewZone() {
        try {
            
            Statement st = connection.createStatement();
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
    public  boolean enterZoneInfo(String ZoneID, String LotName) throws SQLException {
        try {
//          Connection conn = DbConnection.getConnection();
            String query = "INSERT INTO Zone (ZoneID, LotName) VALUES (?,?)";
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, ZoneID);
            st.setString(2, LotName);
            st.executeUpdate();

            return true;
        } catch (SQLException ex) {
          //  ex.printStackTrace();
            return false;
        }
    }

    public Boolean updateZoneInfo(String ZoneID, String LotName) {
        try {
            String query = "UPDATE Zone SET LotName = ? WHERE ZoneID = ?";

            try (PreparedStatement st = connection.prepareStatement(query)) {
                st.setString(1, ZoneID);
                st.setString(2, LotName);
                st.executeUpdate();

                String countQuery = "SELECT COUNT(*) AS count_val FROM Zone WHERE LotName = ? AND ZoneID = ?";
                try (PreparedStatement countSt = connection.prepareStatement(countQuery)) {
                    countSt.setString(1, LotName);
                    countSt.setString(2, ZoneID);

                    ResultSet rs = countSt.executeQuery();

                    int count = 0;
                    while (rs.next()) {
                        count = rs.getInt("count_val");
                    }

                    return count != 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public  Boolean deleteZoneInfo(String ZoneID, String LotName) {
        try {
            

        	String query = "DELETE FROM Zone WHERE ZoneID = ? AND LotName = ?";
        	PreparedStatement st = connection.prepareStatement(query);
        	  st.setString(1, ZoneID);
              st.setString(2, LotName);
              int rowsAffected = st.executeUpdate();
        	return Boolean.valueOf(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Boolean.valueOf(false);
        }
    }
}