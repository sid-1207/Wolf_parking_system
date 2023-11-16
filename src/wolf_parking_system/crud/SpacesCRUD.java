package wolf_parking_system.crud;
import java.util.Map;
import java.util.Set;
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



//    public Boolean updatecustomizedSpaces(String SpaceType, Boolean Availability, Map<String, Object> conditions) {
//        try {
//            StringBuilder queryBuilder = new StringBuilder("UPDATE Spaces SET SpaceType=?, Availability=?");
//
//            if (conditions != null && !conditions.isEmpty()) {
//                queryBuilder.append(" WHERE ");
//                Set<Map.Entry<String, Object>> entrySet = conditions.entrySet();
//
//                int conditionCount = 0;
//                for (Map.Entry<String, Object> entry : entrySet) {
//                    if (conditionCount > 0) {
//                        queryBuilder.append(" AND ");
//                    }
//
//                    queryBuilder.append(entry.getKey()).append("=?");
//                    conditionCount++;
//                }
//            }
//
//            try (PreparedStatement st = connection.prepareStatement(queryBuilder.toString())) {
//                st.setString(1, SpaceType);
//                st.setBoolean(2, Availability);
//
//                int parameterIndex = 3;
//                for (Object value : conditions.values()) {
//                    st.setObject(parameterIndex, value);
//                    parameterIndex++;
//                }
//
//                // Execute the update
//                int rowsAffected = st.executeUpdate();
//
//                // Check if any rows were affected to determine if the update was successful
//                return rowsAffected > 0;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }

    
    public boolean AddSpace(String ZoneID, String LotName, Integer SpaceNumber, String SpaceType, Boolean Availability) {
        try {
            // Check if the specified ZoneID and LotName exist in the Zone table
            if (!zoneExists(ZoneID, LotName)) {
                System.out.println("Zone with ID " + ZoneID + " and LotName " + LotName + " does not exist.");
                return false;
            }
            // Check if the specified ZoneID, LotName, and SpaceNumber combination already exists
            if (spaceExists(ZoneID, LotName, SpaceNumber)) {
                System.out.println("Space with ZoneID " + ZoneID + ", LotName " + LotName +
                        ", and SpaceNumber " + SpaceNumber + " already exists.");
                return false;
            }

            String query = "INSERT INTO Spaces(ZoneID, LotName, SpaceNumber, SpaceType, Availability) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement st = connection.prepareStatement(query)) {
                st.setString(1, ZoneID);
                st.setString(2, LotName);
                st.setInt(3, SpaceNumber);
                st.setString(4, SpaceType);
                st.setBoolean(5, Availability);
                st.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Boolean updateSpaces(String ZoneID, String LotName, Integer SpaceNumber, String SpaceType, Boolean Availability) {
        try {
            String query = "UPDATE Spaces SET SpaceType=?, Availability=? WHERE ZoneID=? AND LotName=? AND SpaceNumber=?";
            try (PreparedStatement st = connection.prepareStatement(query)) {
                st.setString(1, SpaceType);
                st.setBoolean(2, Availability);
                st.setString(3, ZoneID);
                st.setString(4, LotName);
                st.setInt(5, SpaceNumber);

                // Execute the update
                int rowsAffected = st.executeUpdate();

                // Check if any rows were affected to determine if the update was successful
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

        public Boolean deleteSpace(String ZoneID, String LotName, Integer SpaceNumber) {
            try {
                String query = "DELETE FROM Spaces WHERE ZoneID=? AND LotName=? AND SpaceNumber=?";
                try (PreparedStatement st = connection.prepareStatement(query)) {
                    st.setString(1, ZoneID);
                    st.setString(2, LotName);
                    st.setInt(3, SpaceNumber);
                    int rowsAffected = st.executeUpdate();
                    
                    // Check if any rows were affected to determine if the delete was successful
                    return rowsAffected > 0;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        }

        private boolean zoneExists(String ZoneID, String LotName) throws SQLException {
            String query = "SELECT COUNT(*) AS count_val FROM Zone WHERE ZoneID = ? AND LotName = ?";
            try (PreparedStatement countSt = connection.prepareStatement(query)) {
                countSt.setString(1, ZoneID);
                countSt.setString(2, LotName);

                ResultSet rs = countSt.executeQuery();

                int count = 0;
                while (rs.next()) {
                    count = rs.getInt("count_val");
                }

                return count != 0;
            }
        }

private boolean spaceExists(String ZoneID, String LotName, Integer SpaceNumber) throws SQLException {
    String query = "SELECT * FROM Spaces WHERE ZoneID = ? AND LotName = ? AND SpaceNumber = ?";
    try (PreparedStatement st = connection.prepareStatement(query)) {
        st.setString(1, ZoneID);
        st.setString(2, LotName);
        st.setInt(3, SpaceNumber);
        try (ResultSet rs = st.executeQuery()) {
            return rs.next();
        }
    }
}
}

