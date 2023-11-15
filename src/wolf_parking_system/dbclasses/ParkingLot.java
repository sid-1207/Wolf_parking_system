package wolf_parking_system.dbclasses;
public class ParkingLot {

    public String LotName;
    public String Address;

    public ParkingLot(String LotName,String Address)
    {
        this.LotName = LotName;
        this.Address = Address;
    }
    public String getLotName() { return this.LotName; }
    public String getAddress() { return this.Address; }

    public void setLotName( String LotName) {  this.LotName =  LotName;}
    public void setAddress(String Address) {  this.Address =  Address;}
}
