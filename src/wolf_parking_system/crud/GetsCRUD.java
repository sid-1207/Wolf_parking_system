package wolf_parking_system.crud;

import java.sql.*;
import java.util.ArrayList;

import wolf_parking_system.*;
import wolf_parking_system.dbclasses.Gets;

public class GetsCRUD {

    private  Statement statement;
    private Connection connection;
    private ResultSet result;
    public GetsCRUD(Statement statement,Connection connection,ResultSet result){
        this.statement=statement;
        this.connection=connection;
        this.result=result;
    }
    
    //read
    public  ArrayList<Gets> viewGets() {
        try {
            
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("Select * from Gets");
            ArrayList<Gets> list = new ArrayList<>();
            while (rs.next()) {
                Gets p = new Gets(rs.getString("CarLicenseNumber"), rs.getString("CitationNumber"));
                list.add(p);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //create
    public  boolean enterGetsInfo(String CarLicenseNumber, String CitationNumber) throws SQLException {
        try {
    //      Connection conn = DbConnection.getConnection();
            String query = "INSERT INTO Gets (CarLicenseNumber, CitationNumber) VALUES (?,?)";
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, CarLicenseNumber);
            st.setString(2, CitationNumber);
            st.executeUpdate();

            return true;
        } catch (SQLException ex) {
            //  ex.printStackTrace();
            return false;
        }
    }

    //No Update Function as Both Attributes are Primary Keys

    //delete
    public  Boolean deleteGetsInfo(String CarLicenseNumber, String CitationNumber) {
        try {
            String query = "DELETE FROM Gets WHERE CarLicenseNumber = ? AND CitationNumber = ?";
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, CarLicenseNumber);
            st.setString(2, CitationNumber);
            int rowsAffected = st.executeUpdate();
            return Boolean.valueOf(true);
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
            return Boolean.valueOf(false);
        }
    }
}

