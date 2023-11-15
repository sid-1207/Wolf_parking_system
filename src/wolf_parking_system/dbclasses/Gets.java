package wolf_parking_system.dbclasses;

public class Gets {
    public String CarLicenseNumber;
    public String CitationNumber;

    public Gets(String CarLicenseNumber, String CitationNumber) {
        this.CarLicenseNumber = CarLicenseNumber;
        this.CitationNumber = CitationNumber;
    }
    public String getCarLicenseNumber() {
        return this.CarLicenseNumber;
    }

    public String getCitationNumber() {
        return this.CitationNumber;
    }
    public void setCarLicenseNumber(String CarLicenseNumber) {
        this.CarLicenseNumber = CarLicenseNumber;
    }

    public void setCitationNumber(String CitationNumber) {
        this.CitationNumber = CitationNumber;
    }
}
