package wolf_parking_system.dbclasses;

public class AppealsOrPays {
    public String CitationNumber;
    public Long DriverID;
  
      public AppealsOrPays(String CitationNumber,Long DriverID)
      {
          this.CitationNumber= CitationNumber;
          this.DriverID = DriverID;
      }
      public Long getDriverID() { return this.DriverID; }
      public String getCitationNumber() { return this.CitationNumber; }
  
      public void setDriverID(Long DriverID){ this.DriverID=DriverID; }
      public void setCitationNumber(String CitationNumber){ this.CitationNumber=CitationNumber; }
    
}
