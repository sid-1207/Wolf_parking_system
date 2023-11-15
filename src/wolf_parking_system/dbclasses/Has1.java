package wolf_parking_system.dbclasses;

public class Has1 {
    public long DriverID;
    public int NumberOfPermits;

    public Has1(long DriverID,int NumberOfPermits)
    {
        this.DriverID = DriverID;
        this.NumberOfPermits = NumberOfPermits;
    }
    public Long getDriverID() { return this.DriverID; }
    public Integer NumberOfPermits() { return this.NumberOfPermits; }


    public void setDriverID( Long DriverID) {  this.DriverID =  DriverID;}
    public void setName(int NumberOfPermits) {  this.NumberOfPermits = NumberOfPermits;}

    
}
