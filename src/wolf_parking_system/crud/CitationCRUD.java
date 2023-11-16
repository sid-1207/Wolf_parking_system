package wolf_parking_system.crud;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

import wolf_parking_system.dbclasses.Citation1;

public class CitationCRUD {
       // view citations
       private  Statement statement;
       private Connection connection;
       private ResultSet result;
       public CitationCRUD(Statement statement,Connection connection,ResultSet result){
           this.statement=statement;
           this.connection=connection;
           this.result=result;
   }
    public  ArrayList<Citation1> getCitation() {
       
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("Select * from Citation1");
            ArrayList<Citation1> list = new ArrayList<>();
            while (rs.next()) {
                Citation1 p = new Citation1(rs.getString("CitationNumber"),
                rs.getString("PaymentStatus"),
                rs.getBoolean("AppealStatus"),
                rs.getDate("CitationDate"),
                rs.getTime("CitationTime"),
                rs.getString("LotName"),
                rs.getString("Category"));
                list.add(p);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

// insert in Citation
        public Boolean addCitation(String CitationNumber,String PaymentStatus,Boolean AppealStatus,Date CitationDate,Time CitationTime, String LotName, String Category) {
            try {
           
                String query = "INSERT INTO Citation1 (CitationNumber, PaymentStatus, AppealStatus, CitationDate, CitationTime, LotName, Category) VALUES (?,?,?,?,?,?,?);\n" + //
                        "";
                PreparedStatement st = connection.prepareStatement(query);
                st.setString(1, CitationNumber);
                st.setString(2, PaymentStatus);
                st.setBoolean(3, AppealStatus);
                st.setDate(4, CitationDate);
                st.setTime(5, CitationTime);
                st.setString(6, LotName);
                st.setString(7, Category);
                st.executeUpdate();
                // ResultSet rs = st.executeQuery("select  LotName from ParkingLot"); //Is this for displaying data being inserted or not
                // while (rs.next())
                //     LotName = rs.getString("LotName");
                return true;
            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        }

//update Citation
    
        public Boolean updateCitation(String CitationNumber,String PaymentStatus,Boolean AppealStatus,Date CitationDate,Time CitationTime, String LotName, String Category) {
            try {
                
                String query= "UPDATE Citation1 SET PaymentStatus=?,AppealStatus=?, CitationDate=?,CitationTime=?, LotName=?, Category=? WHERE  CitationNumber=?";

                PreparedStatement st = connection.prepareStatement(query);
            
                st.setString(1, PaymentStatus);
                st.setBoolean(2, AppealStatus);
                st.setDate(3, CitationDate);
                st.setTime(4, CitationTime);
                st.setString(5, LotName);
                st.setString(6, Category);
                st.setString(7, CitationNumber);
                st.executeUpdate();
                // ResultSet rs = st.executeQuery("Select count(*) as count_val from ParkingLot where Address="+ Address);
                // int count = 0;
                // while (rs.next()) {
                //     count = rs.getInt("count_val");
    
                // }
                // if (count!=0){
                //     return Boolean.valueOf(true);;
                // }
                // return Boolean.valueOf(false);;
                // //return Boolean.valueOf(true);
                return Boolean.valueOf(true);
            } catch (SQLException e) {
                e.printStackTrace();
                return Boolean.valueOf(false);
            }
        }
    
        // public static Boolean deleteParkingLot(String LotName,String Address) {
        //     try {
        //         Connection conn = conn.getConnection();
        //         String query = "DELETE FROM ParkingLot WHERE Address ";
        //         PreparedStatement st = conn.prepareStatement(query);
        //         st.setString(1, LotName);
        //         st.setString(2, Address);
        //         st.executeUpdate();
        //         return true;
    
        //     } catch (SQLException ex) {
        //         ex.printStackTrace();
        //         return Boolean.valueOf(false);
        //     }
        // }
}
