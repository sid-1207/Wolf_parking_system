package wolf_parking_system.crud;

import java.sql.*;
import java.util.ArrayList;
import wolf_parking_system.dbclasses.*;
import wolf_parking_system.connection.*;


public class ParkingLotCRUD {
    // view parking lots
    private  Statement statement;
    private Connection connection;
    private ResultSet result;
    public ParkingLotCRUD(Statement statement,Connection connection,ResultSet result){
        this.statement=statement;
        this.connection=connection;
        this.result=result;
}
   
    
    public  ArrayList<ParkingLot> viewParkingLots() {
        try {
          
           
            ResultSet rs = statement.executeQuery("Select * from ParkingLot");
            ArrayList<ParkingLot> list = new ArrayList<>();
            while (rs.next()) {
                ParkingLot p = new ParkingLot(rs.getString("LotName"),rs.getString("Address"));
                list.add(p);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
    			try {
    				connection.close();
    			} catch (SQLException f) {
    				f.printStackTrace();
    			}
    		}
    		if (statement != null) {
    			try {
    				statement.close();
    			} catch (SQLException w) {
    				w.printStackTrace();
    			}
    		}
    		if (result != null) {
    			try {
    				result.close();
    			} catch (SQLException z) {
    				z.printStackTrace();
    			}
    		}
    	}
            return null;
            
        }
    

// insert in parking lots
        public  Boolean insertParkinglot(String LotName,String Address) {
            try {
                 if (lotNameExists(LotName)) {
            System.out.println("LotName already exists.");
            return false;
        }
                
                String query = "insert into ParkingLot(LotName, Address ) values (?,?)";
                PreparedStatement st = connection.prepareStatement(query);
                st.setString(1, LotName);
                st.setString(2, Address);
                st.executeUpdate();
                ResultSet rs = st.executeQuery("select  LotName from ParkingLot"); //Is this for displaying data being inserted or not
                while (rs.next())
                    LotName = rs.getString("LotName");
                return true;
            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        }

//update article  
    
        public Boolean updateParkingLot(String LotName, String Address) {
            try {
                String query = "UPDATE ParkingLot SET Address=? WHERE LotName=?";
                try (PreparedStatement st = connection.prepareStatement(query)) {
                    st.setString(1, Address);
                    st.setString(2, LotName);
                    st.executeUpdate();

                    // Check if the update was successful
                    String countQuery = "SELECT COUNT(*) AS count_val FROM ParkingLot WHERE Address=?";
                    try (PreparedStatement countSt = connection.prepareStatement(countQuery)) {
                        countSt.setString(1, Address);

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
    
        public Boolean deleteParkingLot(String LotName) {
            try {
                String query = "DELETE FROM ParkingLot WHERE LotName=?";
                try (PreparedStatement st = connection.prepareStatement(query)) {
                    st.setString(1, LotName);
                    st.executeUpdate();
                    return true;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        }

//Lot name already exists
    private boolean lotNameExists(String lotName) throws SQLException {
    String query = "SELECT LotName FROM ParkingLot WHERE LotName = ?";
    PreparedStatement checkSt = connection.prepareStatement(query);
    checkSt.setString(1, lotName);
    ResultSet rs = checkSt.executeQuery();
    return rs.next(); // If next() is true, LotName already exists
}
    }
   
    

    



