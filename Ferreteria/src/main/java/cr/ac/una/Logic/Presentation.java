package cr.ac.una.Logic;

public class Presentation {
    private String measure;
    private double quantity;
    private double price;


    public Presentation(String measure, double quantity, double price) {
        this.measure = measure;
        this.quantity = quantity;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double precioVenta(double precioCompra) {
        return precioCompra / quantity;
    }

    public void actualizarCantidad(double cantidadVender) {
        if (this.quantity >= cantidadVender) {
            this.quantity -= cantidadVender;
        } else {
            throw new IllegalArgumentException("Stock insuficiente para la venta.");
        }
    }

    public double descuentoDiezUnidades(int cantidadAVender, double precioVenta) {
        double descuento = 0.0;
        if (cantidadAVender == 10) {
             descuento = precioVenta * 0.10;
        }
        return precioVenta - descuento;
    }
}

