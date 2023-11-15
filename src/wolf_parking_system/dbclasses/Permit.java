import java.time.*;

public class Permit {

    public String PermitID;
    public String PermitType;
    public LocalTime ExpirationTime;
    public LocalDate StartDate;
    public LocalDate EndDate;

    public Permit(String PermitID,String PermitType, LocalTime ExpirationTime, LocalDate StartDate, LocalDate EndDate)
    {
        this.PermitID = PermitID;
        this.PermitType = PermitType;
        this.ExpirationTime = ExpirationTime;
        this.StartDate = StartDate;
        this.EndDate = EndDate;
    }
    public String getPermitID() { return this.PermitID; }
    public String getPermitType() { return this.PermitType; }
    public LocalTime getExpirationTime() { return this.ExpirationTime; }
    public LocalDate getStartDate() { return this.StartDate; }
    public LocalDate getEndDate() { return this.EndDate; }

    public void setPermitID( String PermitID) {  this.PermitID =  PermitID;}
    public void setPermitType(String PermitType) {  this.PermitType =  PermitType;}
    public LocalTime setExpirationTime(LocalTime ExpirationTime) { return this.ExpirationTime; }
    public LocalDate setStartDate(LocalDate StartDate) { return this.StartDate; }
    public LocalDate setEndDate(LocalDate EndDate) { return this.EndDate; }

}