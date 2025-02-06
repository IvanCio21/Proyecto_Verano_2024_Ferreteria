package cr.ac.una.Logic;

import java.util.List;

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

    public static double descuentoPorUnidades(List<Presentation> carrito) {
        double total = 0;
        for (Presentation p : carrito) {
            double totalArticulo = p.getPrice() * p.getQuantity();
            if (p.getQuantity() > 10) { // Mas de 10 unidades del mismo articulo
                totalArticulo *= 0.90;
            }
            total += totalArticulo;
        }
        return total;
    }

    public static double descuentoPorArticulosDiferentes(List<Presentation> carrito) {
        if (carrito.size() > 10) { // Mas de 10 articulos diferentes
            double total = carrito.stream().mapToDouble(p -> p.getPrice() * p.getQuantity()).sum();
            return total * 0.95;
        }
        return carrito.stream().mapToDouble(p -> p.getPrice() * p.getQuantity()).sum();
    }

    public static double descuentoPorMontoTotal(List<Presentation> carrito) {
        double total = carrito.stream().mapToDouble(p -> p.getPrice() * p.getQuantity()).sum();
        if (total > 5000) { // Mas de $5000 en total
            return total * 0.925;
        }
        return total;
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

