package wolf_parking_system.crud;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import wolf_parking_system.dbclasses.*;
import wolf_parking_system.connection.*;


public class ParkingLotCRUD {
    // view parking lots
    public static ArrayList<ParkingLot> viewParkingLots() {
        try {
            Connection conn =conn.getConnection();//i dont know the error
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("Select * from ARTICLE");
            ArrayList<ParkingLot> list = new ArrayList<>();
            while (rs.next()) {
                ParkingLot p = new ParkingLot(rs.getString("LotName"),rs.getString("Address"));
                list.add(p);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

// insert in parking lots
        public static Boolean insertParkinglot(String LotName,String Address) {
            try {
                Connection conn = conn.getConnection();//i dont know the error
                String query = "insert into ParkingLot(LotName, Address ) values (?,?)";
                PreparedStatement st = conn.prepareStatement(query);
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
    
        public static Boolean updateParkingLot(String LotName,String Address) {
            try {
                Connection conn = conn.getConnection();
    //            Update ARTICLE set Text='Qwerty' where PID=2 AND ArticleID=3;
                String query = "Update ParkingLot set LotName=? where Address=? ";
                PreparedStatement st = conn.prepareStatement(query);
                st.setString(1, LotName);
                st.setString(2, Address);
                st.executeUpdate();
                ResultSet rs = st.executeQuery("Select count(*) as count_val from ParkingLot where Address="+ Address);
                int count = 0;
                while (rs.next()) {
                    count = rs.getInt("count_val");
    
                }
                if (count!=0){
                    return Boolean.valueOf(true);;
                }
                return Boolean.valueOf(false);;
                //return Boolean.valueOf(true);
            } catch (SQLException e) {
                e.printStackTrace();
                return Boolean.valueOf(false);
            }
        }
    
        public static Boolean deleteParkingLot(String LotName,String Address) {
            try {
                Connection conn = conn.getConnection();
                String query = "DELETE FROM ParkingLot WHERE Address ";
                PreparedStatement st = conn.prepareStatement(query);
                st.setString(1, LotName);
                st.setString(2, Address);
                st.executeUpdate();
                return true;
    
            } catch (SQLException ex) {
                ex.printStackTrace();
                return Boolean.valueOf(false);
            }
        }
    }
   
    

    

}
