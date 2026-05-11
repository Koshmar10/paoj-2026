package com.pao.laboratory09.exercise3;

public class Tranzactie {
    public final int id;
    public final double suma;
    public final String data;
    public final String tip;
    public final String contSursa;

    public Tranzactie(int id, double suma, String data) {
        this(id, suma, data, null, null);
    }

    public Tranzactie(int id, double suma, String data, String tip, String contSursa) {
        this.id         = id;
        this.suma       = suma;
        this.data       = data;
        this.tip        = tip;
        this.contSursa  = contSursa;
    }

    @Override
    public String toString() {
        return String.format(java.util.Locale.US, "[%d] %s %s: %.2f RON", id, data, tip, suma);
    }
}
