package una.ac.cr.supermercadoapp.domain;

import java.io.Serializable;

public class Descuento implements Serializable {
    private int id;
    private double tarifa;
    private int membresiaid;
    public Membresia getMembresia() {
        return membresia;
    }
    public void setMembresia(Membresia membresia) {
        this.membresia = membresia;
    }
    private Membresia membresia;
    public Descuento() {
    }

    public Descuento(int id, double tarifa, int membresiaid) {
        this.id = id;
        this.tarifa = tarifa;
        this.membresiaid = membresiaid;
    }

    public Descuento(double tarifa, int membresiaid) {
        this.tarifa = tarifa;
        this.membresiaid = membresiaid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTarifa() {
        return tarifa;
    }

    public void setTarifa(float tarifa) {
        this.tarifa = tarifa;
    }

    public int getMembresiaid() {
        return membresiaid;
    }

    public void setMembresiaid(int membresiaid) {
        this.membresiaid = membresiaid;
    }
}
