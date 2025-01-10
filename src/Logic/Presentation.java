package Logic;

public class Presentation {
    private int PresentationID;
    private String measure;
    private double quantity;


    public Presentation(int presentationID, String measure, double quantity) {
        PresentationID = presentationID;
        this.measure = measure;
        this.quantity = quantity;
    }

    public int getPresentationID() {
        return PresentationID;
    }

    public void setPresentationID(int presentationID) {
        PresentationID = presentationID;
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
