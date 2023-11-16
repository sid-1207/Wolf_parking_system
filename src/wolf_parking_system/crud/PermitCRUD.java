
package wolf_parking_system.crud;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import wolf_parking_system.dbclasses.*;

public class PermitCRUD {
    private Statement statement;
    private Connection connection;
    private ResultSet result;

    public PermitCRUD(Statement statement, Connection connection, ResultSet result) {
        this.statement = statement;
        this.connection = connection;
        this.result = result;
    }

    public ArrayList<Permit> viewPermit() {
        try {

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("Select * from Permit");
            ArrayList<Permit> list = new ArrayList<>();
            while (rs.next()) {
                Permit p = new Permit(rs.getString("PermitID"), rs.getString("PermitType"),
                        rs.getString("ExpirationTime"), rs.getString("StartDate"), rs.getString("EndDate"));
                list.add(p);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Boolean enterPermitInfo(String PermitID, String PermitType, Time ExpirationTime, Date startDate, Date endDate) {
        try {
            String query = "insert into Permit (PermitID, PermitType, ExpirationTime, StartDate, EndDate) values (?,?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, PermitID);
            st.setString(2, PermitType);
            st.setTime(3, ExpirationTime); // Use st.setTime for Time type
            st.setDate(4, startDate); // Use st.setDate for Date type
            st.setDate(5, endDate); // Use st.setDate for Date type
            st.executeUpdate();
            
            // No need to execute another query to get PermitID, you can get it from the input parameter
            // String permit_id = PermitID;

            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }


    public Boolean updatePermitInfo(String PermitID, String PermitType, Time ExpirationTime, Date startDate, Date endDate) {
        try {
           
            String query = "Update Permit SET PermitType=?,ExpirationTime=?,startDate=?,endDate =?  WHERE PermitID=?";
            try(PreparedStatement st = connection.prepareStatement(query)){
            	st.setString(1, PermitType);
            	st.setTime(2, ExpirationTime);
            	st.setDate(3, startDate);
            	st.setDate(4, endDate);
            st.setString(5, PermitID);
            st.executeUpdate();
            String countQuery = "SELECT COUNT(*) AS count_val FROM Permit WHERE PermitID=?";
            try (PreparedStatement countSt = connection.prepareStatement(countQuery)) {
                countSt.setString(1,PermitID);
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

    public Boolean deletePermitInfo(String PermitID) {
        try {

            String query = "DELETE FROM Permit WHERE PermitID=?";
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, PermitID);
            st.executeUpdate();
            return Boolean.valueOf(true);

        } catch (SQLException ex) {
            ex.printStackTrace();
            return Boolean.valueOf(false);
        }
    }
}