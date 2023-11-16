package wolf_parking_system.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.ArrayList;
import wolf_parking_system.dbclasses.Vehicle;
import wolf_parking_system.dbclasses.Zone;
import wolf_parking_system.connection.*;

public class VehicleCRUD {
    private  Statement statement;
    private Connection connection;
    private ResultSet result;
    public VehicleCRUD(Statement statement,Connection connection,ResultSet result){
        this.statement=statement;
        this.connection=connection;
        this.result=result;
}
    
    //READ
    public ArrayList<Vehicle> viewVehicles() {
        try {
   
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Vehicle;");
            ArrayList<Vehicle> list = new ArrayList<>();
            while (rs.next()) {
                Vehicle p = new Vehicle(rs.getString("CarLicenseNumber"), rs.getString("Model"), rs.getInt("Year"),rs.getString("Manufacturer"), rs.getString("Color"), rs.getLong("DriverID"));
                list.add(p);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //CREATE
    public  boolean AddVehicle(String CarLicenseNumber,String Model,Integer Year,String Manufacturer,String Color,Long DriverID) {
        boolean state = false;
        try {
            
            String query = "Insert into Vehicle(CarLicenseNumber, Model, Year, Manufacturer, Color, DriverID) values (?,?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, CarLicenseNumber);
            st.setString(2, Model);
            st.setInt(3, Year);
            st.setString(4, Manufacturer);
            st.setString(5, Color);
            st.setLong(6, DriverID);
            st.executeUpdate();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    //UPDATE
    public  Boolean updateVehicle(String CarLicenseNumber,String Model,Integer Year,String Manufacturer,String Color,Long DriverID) {
        try {
           
            String query = "UPDATE Vehicle SET Model=?, Year=?, Manufacturer=?, Color=?, DriverID=? WHERE (CarLicenseNumber ="+CarLicenseNumber+")";
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, Model);
            st.setInt(2, Year);
            st.setString(3, Manufacturer);
            st.setString(4, Color);
            st.setLong(5, DriverID);
            st.executeUpdate();
            return Boolean.valueOf(true);
        } catch (SQLException e) {
            e.printStackTrace();
            return Boolean.valueOf(false);
        }
    }

    //DELETE
    public Boolean deleteVehicle(String CarLicenseNumber) {
        try {
        
            Statement st = connection.createStatement();
            st.executeUpdate("DELETE FROM Vehicle WHERE (CarLicenseNumber=" + CarLicenseNumber);
            return Boolean.valueOf(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Boolean.valueOf(false);
        }
    }    
}
