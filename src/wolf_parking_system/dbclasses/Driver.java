package wolf_parking_system.dbclasses;

import java.util.function.IntToLongFunction;

public class Driver {
    public long DriverID;
    public String Name;
    public boolean Handicap;
    public String Status;

    public Driver(long DriverID,String Name,boolean Handicap, String Status)
    {
        this.DriverID = DriverID;
        this.Name = Name;
        this.Handicap = Handicap ;
        this.Status = Status;
    }
    public Long getDriverID() { return this.DriverID; }
    public String getName() { return this.Name; }
    public Boolean getHandicap() { return this.Handicap; }
    public String getStatus() {return this.Status; }

    public void setDriverID( Long DriverID) {  this.DriverID =  DriverID;}
    public void setName(String Name) {  this.Name =  Name;}
    public void setHandicap(Boolean Handicap) {  this.Handicap = Handicap;}
    public void setStatus(String Status) {this.Status= Status; }
}
