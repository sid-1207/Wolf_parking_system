package wolf_parking_system.crud;

import java.sql.*;
import java.util.*;
import wolf_parking_system.dbclasses.Driver;


public class DriverCRUD {
    private  Statement statement;
    private Connection connection;
    private ResultSet result;
    public DriverCRUD(Statement statement,Connection connection,ResultSet result){
        this.statement=statement;
        this.connection=connection;
        this.result=result;
}

    public ArrayList<Driver> viewDriver() {
        try {
    
            Statement st = connection.createStatement();
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

    public  Boolean enterDriverInfo(Long DriverID, String Name, Boolean Handicap, String Status) {
        try {
          
            String query = "insert into Driver (DriverID, Name, Handicap, Status) values (?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(query);
            st.setLong(1, DriverID);
            st.setString(2, Name);
            st.setBoolean(3, Handicap);
            st.setString(4, Status);
            st.executeUpdate();
            ResultSet rs = st.executeQuery("select  DriverID from Driver"); //Is this for displaying data being inserted or not
            while (rs.next())
                 DriverID= rs.getLong("DriverID");
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public  Boolean updateDriverInfo(Boolean Handicap,Long DriverID, String Name) {
        try {
            
            String query = "Update Driver set Handicap=?  where DriverID=? AND Name=?";
            

            try (PreparedStatement st = connection.prepareStatement(query);) {
                st.setBoolean(1, Handicap);
                st.setLong(2, DriverID);
                st.setString(3, Name);
                // st.setString(4, Status);
                st.executeUpdate();
                String countQuery = "Select count(*) as count_val from Driver where DriverID=? AND Name=?";
                try (PreparedStatement countSt = connection.prepareStatement(countQuery)) {
                    countSt.setLong(1,DriverID);
                    countSt.setString(2,Name);

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
            return Boolean.valueOf(false);
        }
    }

    public  Boolean deleteDriverInfo(Long DriverID) {
        try {
            
            String query = "DELETE FROM Driver WHERE DriverID=?";
            PreparedStatement st = connection.prepareStatement(query);
            st.setLong(1, DriverID);
            st.executeUpdate();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return Boolean.valueOf(false);
        }
    }
}
