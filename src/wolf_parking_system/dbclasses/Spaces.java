package wolf_parking_system.dbclasses;

public class Spaces {
    public String ZoneID;
    public String LotName;
    public Integer SpaceNumber;
    public String SpaceType;
    public Boolean Availability;

    public Spaces(String ZoneID, String LotName, Integer SpaceNumber, String SpaceType, Boolean Availability) {
        this.ZoneID = ZoneID;
        this.LotName = LotName;
        this.SpaceNumber = SpaceNumber;
        this.SpaceType = SpaceType;
        this.Availability = Availability;
    }

    public String getZoneID() {
        return this.ZoneID;
    }

    public String getLotName() {
        return this.LotName;
    }

    public Integer getSpaceNumber() {
        return this.SpaceNumber;
    }

    public String getSpaceType() {
        return this.SpaceType;
    }

    public Boolean getAvailability() {
        return this.Availability;
    }

    public void setZoneID(String ZoneID) {
        this.ZoneID = ZoneID;
    }

    public void setLotName(String LotName) {
        this.LotName = LotName;
    }

    public void setSpaceNumber(Integer SpaceNumber) {
        this.SpaceNumber = SpaceNumber;
    }

    public void setSpaceType(String SpaceType) {
        this.SpaceType = SpaceType;
    }

    public void setAvailability(Boolean Availability) {
        this.Availability = Availability;
    }
}
