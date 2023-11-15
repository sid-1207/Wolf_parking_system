public class Citation2 {

    public Integer Fee;
    public String Category;

    public Citation2(Integer Fee,String Category)
    {
        this.Fee = Fee;
        this.Category = Category;
    }
    public Integer getFee() { return this.Fee; }
    public String getCategory() { return this.Category; }

    public void setFee( Integer Fee) {  this.Fee =  Fee;}
    public void setCategory(String Category) {  this.Category =  Category;}
}
