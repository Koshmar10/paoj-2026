package com.pao.laboratory06.exercise3;

import java.util.ArrayList;
import java.util.List;

public class PersoanaJuridica extends Persoana implements PlataOnlineSMS {
    private List<String> smsTrimise = new ArrayList<>();
    private double sold;

    public PersoanaJuridica(String nume, String prenume, String telefon, double sold) {
        super(nume, prenume, telefon);
        this.sold = sold;
    }

    @Override
    public boolean trimiteSMS(String mesaj) {
        // returnează false dacă nu are telefon valid sau mesaj null/gol
        if (telefon == null || telefon.isEmpty()) return false;
        if (mesaj == null || mesaj.isEmpty()) return false;
        smsTrimise.add(mesaj);
        return true;
    }

    @Override
    public void autentificare(String user, String parola) {
        if (user == null || user.isEmpty() || parola == null || parola.isEmpty())
            throw new IllegalArgumentException("User și parola nu pot fi null/gole.");
    }

    @Override
    public double consultareSold() {
        return sold;
    }

    @Override
    public boolean efectuarePlata(double suma) {
        if (suma <= 0) throw new IllegalArgumentException("Suma trebuie să fie pozitivă.");
        if (suma > sold) return false;
        sold -= suma;
        return true;
    }

    public List<String> getSmsTrimise() { return smsTrimise; }
}
