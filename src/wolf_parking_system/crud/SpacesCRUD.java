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
    private  Statement statement;
    private Connection connection;
    private ResultSet result;
    public SpacesCRUD(Statement statement,Connection connection,ResultSet result){
        this.statement=statement;
        this.connection=connection;
        this.result=result;
}

    public ArrayList<Spaces> viewSpaces() {
        try {
            
            Statement st = connection.createStatement();
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

        public boolean AddSpace(String ZoneID, String LotName, Integer SpaceNumber, String SpaceType, Boolean Availability) {
            boolean state = false;
            try {
               
                String query = "insert into Spaces(ZoneID, LotName, SpaceNumber, SpaceType, Availability) values (?,?,?,?,?)";
                PreparedStatement st = connection.prepareStatement(query);
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

        public Boolean updateSpaces(String ZoneID, String LotName, Integer SpaceNumber, String SpaceType, Boolean Availability) {
            try {
                
                String query = "UPDATE Spaces SET SpaceType=?, Availability=? WHERE (ZoneID ="+ZoneID+ " AND LotName ="+ LotName+ "AND Space Number = " + SpaceNumber + ")";
                PreparedStatement st = connection.prepareStatement(query);
                st.setString(4, SpaceType);
                st.setBoolean(5, Availability);
                st.executeUpdate();
                return Boolean.valueOf(true);
            } catch (SQLException e) {
                e.printStackTrace();
                return Boolean.valueOf(false);
            }
        }

        public Boolean deleteSpace(String ZoneID, String LotName, Integer SpaceNumber) {
            try {
                
                Statement st = connection.createStatement();
                st.executeUpdate("DELETE FROM Spaces WHERE (ZoneID=" + ZoneID + " AND LotName = " + LotName + " AND Space Number = " + SpaceNumber + ")");
                return Boolean.valueOf(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
                return Boolean.valueOf(false);
            }
        }
}