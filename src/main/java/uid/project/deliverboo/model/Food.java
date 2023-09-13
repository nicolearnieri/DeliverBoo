package uid.project.deliverboo.model;

public class Food {
    private String itName, engName, itDescr, engDescr, path;
    private double price;

    public String getItName() {
        return itName;
    }

    public void setItName(String itName) {
        this.itName = itName;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getItDescr() {
        return itDescr;
    }

    public void setItDescr(String itDescr) {
        this.itDescr = itDescr;
    }

    public String getEngDescr() {
        return engDescr;
    }

    public void setEngDescr(String engDescr) {
        this.engDescr = engDescr;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPrice() {
        String priceS= Double.toString(price);
        return priceS;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
