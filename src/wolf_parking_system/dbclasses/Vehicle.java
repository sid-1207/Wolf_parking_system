package wolf_parking_system.dbclasses;

public class Vehicle {
  public String CarLicenseNumber;
  public String Model;
  public Integer Year;
  public String Manufacturer;
  public String Color;
  public Long DriverID;

    public Vehicle(String CarLicenseNumber,String Model,Integer Year,String Manufacturer,String Color,Long DriverID)
    {
        this.DriverID = DriverID;
        this.Model = Model;
        this.Manufacturer = Manufacturer ;
        this.Year = Year;
        this.Color=Color;
        this.CarLicenseNumber=CarLicenseNumber;
    }
    public Long getDriverID() { return this.DriverID; }
    public String getModel() { return this.Model; }
    public String getCarLicenseNumber() { return this.CarLicenseNumber; }
    public Integer getYear() {return this.Year; }
    public String getManufacturer() { return this.Manufacturer; }
    public String getColor() { return this.Color; }

    public void setDriverID(Long DriverID) { this.DriverID=DriverID; }
    public void setModel(String Model) { this.Model=Model; }
    public void setCarLicenseNumber(String CarLicenseNumber) { this.CarLicenseNumber=CarLicenseNumber;}
    public void setYear(Integer Year) {this.Year=Year; }
    public void setManufacturer(String Manufacturer) { this.Manufacturer=Manufacturer; }
    public void setColor(String Color) { this.Color=Color; }
}
