package una.ac.cr.supermercadoapp.domain;

import java.io.Serializable;

public class Descuento implements Serializable {
    private int id;
    private float tarifa;
    private int membresiaid;

    public Descuento() {
    }

    public Descuento(int id, float tarifa, int membresiaid) {
        this.id = id;
        this.tarifa = tarifa;
        this.membresiaid = membresiaid;
    }

    public Descuento(float tarifa, int membresiaid) {
        this.tarifa = tarifa;
        this.membresiaid = membresiaid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTarifa() {
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
