package Logic;

public class Presentation {
    private String measure;
    private double quantity;

    public Presentation() {
    }

    public Presentation(String measure, double quantity) {
        this.measure = measure;
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
