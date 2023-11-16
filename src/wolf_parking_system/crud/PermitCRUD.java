package wolf_parking_system.crud;

import java.sql.Connection;
import java.time.*;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import wolf_parking_system.dbclasses.*;

public class PermitCRUD {
    private  Statement statement;
    private Connection connection;
    private ResultSet result;
    public PermitCRUD(Statement statement,Connection connection,ResultSet result){
        this.statement=statement;
        this.connection=connection;
        this.result=result;
}

    public  ArrayList<Permit> viewPermit() {
        try {
           
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("Select * from Permit");
            ArrayList<Permit> list = new ArrayList<>();
            while (rs.next()) {
                Permit p = new Permit(rs.getString("PermitID"), rs.getString("PermitType"), rs.getString ("ExpirationTime"), rs.getString("StartDate"), rs.getString("EndDate"));
                list.add(p);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Boolean enterPermitInfo(String PermitID, String PermitType, String ExpirationTime, String StartDate, String EndDate) {
        try {
        
            String query = "insert into Permit (PermitID, PermitType, ExpirationTime, StartDate, EndDate) values (?,?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, PermitID);
            st.setString(2, PermitType);
            st.setString(3, ExpirationTime);
            st.setString(4, StartDate);
            st.setString(5, EndDate);
            st.executeUpdate();
            ResultSet rs = st.executeQuery();
            String permit_id = "";
            while (rs.next())
                permit_id = rs.getString("PermitID");
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Boolean updatePermitInfo(String PermitID, String PermitType, String ExpirationTime, String StartDate, String EndDate) {
        try {
           
            String query = "Update Permit set EndDate =?  where PermitID=?";
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, EndDate);
            st.setString(2, PermitID);
            st.executeUpdate();
            ResultSet rs = st.executeQuery("Select count(*) as count_val from Permit where PermitID="+ PermitID + " AND PermitType =" + PermitType + "AND ExpirationTime =" + ExpirationTime + "AND StartDate =" + StartDate);
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

    public  Boolean deletePermitInfo(String PermitID) {
        try {
        
            String query = "DELETE FROM Permit WHERE PermitID=?";
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, PermitID);
            st.executeUpdate();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return Boolean.valueOf(false);
        }
    }
}