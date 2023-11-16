package wolf_parking_system.dbclasses;
import java.time.*;

public class Permit {

    public String PermitID;
    public String PermitType;
    public String ExpirationTime;
    public String StartDate;
    public String EndDate;

    public Permit(String PermitID,String PermitType, String ExpirationTime, String StartDate, String EndDate)
    {
        this.PermitID = PermitID;
        this.PermitType = PermitType;
        this.ExpirationTime = ExpirationTime;
        this.StartDate = StartDate;
        this.EndDate = EndDate;
    }
    public String getPermitID() { return this.PermitID; }
    public String getPermitType() { return this.PermitType; }
    public String getExpirationTime() { return this.ExpirationTime; }
    public String getStartDate() { return this.StartDate; }
    public String getEndDate() { return this.EndDate; }

    public void setPermitID( String PermitID) {  this.PermitID =  PermitID;}
    public void setPermitType(String PermitType) {  this.PermitType =  PermitType;}
    public void setExpirationTime(String ExpirationTime) {  this.ExpirationTime = ExpirationTime; }
    public void setStartDate(String StartDate) {  this.StartDate = StartDate; }
    public void setEndDate(String EndDate) {  this.EndDate = EndDate; }

}
