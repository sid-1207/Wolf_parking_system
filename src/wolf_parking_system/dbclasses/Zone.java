package wolf_parking_system.dbclasses;
public class Zone {
    public String ZoneID;
    public String LotName;
    
    public Zone(String ZoneID, String LotName)
    {
        this.ZoneID=ZoneID;
        this.LotName=LotName;
    }

    public String getZoneID() {
        return this.ZoneID;
    }
    public String getLotName() {
        return this.LotName;
    }
    
    public void setZoneID(String ZoneID) {
        this.ZoneID=ZoneID;
    }

    public void setLotName(String LotName) {
        this.LotName=LotName;
    }
}
