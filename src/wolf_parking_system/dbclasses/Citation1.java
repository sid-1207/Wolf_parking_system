package wolf_parking_system.dbclasses;

import java.sql.Date;
import java.sql.Time;

public class Citation1 {
    public String CitationNumber;
    public String PaymentStatus;
    public Boolean AppealStatus;
    public Date CitationDate;
    public Time CitationTime;
    public String LotName;
    public String Category;

    public Citation1(String CitationNumber,String PaymentStatus,Boolean AppealStatus, Date CitationDate,Time CitationTime, String LotName, String Category)
    {
        this.CitationNumber = CitationNumber;
        this.PaymentStatus = PaymentStatus;
        this.AppealStatus = AppealStatus;
        this.CitationDate = CitationDate;
        this.CitationTime = CitationTime;
        this.LotName = LotName;
        this.Category = Category;
    }

    public String getCitationNumber() {
        return this.CitationNumber;
    }
    
    public String getPaymentStatus() {
        return this.PaymentStatus;
    }
    
    public Boolean getAppealStatus() {
        return this.AppealStatus;
    }
    
    public Date getCitationDate() {
        return this.CitationDate;
    }
    
    public Time getCitationTime() {
        return this.CitationTime;
    }
    
    public String getLotName() {
        return this.LotName;
    }
    
    public String getCategory() {
        return this.Category;
    }
    
    public void setCitationNumber(String CitationNumber) {
        this.CitationNumber = CitationNumber;
    }
    
    public void setPaymentStatus(String PaymentStatus) {
        this.PaymentStatus = PaymentStatus;
    }
    
    public void setAppealStatus(Boolean AppealStatus) {
        this.AppealStatus = AppealStatus;
    }
    
    public void setCitationDate(Date CitationDate) {
        this.CitationDate = CitationDate;
    }
    
    public void setCitationTime(Time CitationTime) {
        this.CitationTime = CitationTime;
    }
    
    public void setLotName(String LotName) {
        this.LotName = LotName;
    }
    
    public void setCategory(String Category) {
        this.Category = Category;
    }
    
}
