public class Has2 {

    public String DriverID;
    public String CarLicenseNumber;
    public String PermitID;

    public Has2(String DriverID,String CarLicenseNumber, String PermitID)
    {
        this.DriverID = DriverID;
        this.CarLicenseNumber = CarLicenseNumber;
        this.PermitID = PermitID;
    }
    public String getDriverID() { return this.DriverID; }
    public String getCarLicenseNumber() { return this.CarLicenseNumber; }
    public String getPermitID() { return this.PermitID; }

    public void setDriverID( String DriverID) {  this.DriverID =  DriverID;}
    public void setCarLicenseNumber(String CarLicenseNumber) {  this.CarLicenseNumber =  CarLicenseNumber;}
    public String setPermitID(String PermitID) { return this.PermitID; }

}