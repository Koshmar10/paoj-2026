package com.pao.laboratory09.exercise3;

import java.util.Locale;

public class ATMThread extends Thread {
    private final int atmId;
    private final CoadaTranzactii coada;
    private static int nextTranzactieId = 1;

    public ATMThread(int atmId, CoadaTranzactii coada) {
        this.atmId = atmId;
        this.coada = coada;
    }

    @Override
    public void run() {
        String name = "ATM-" + atmId;
        for (int i = 0; i < 4; i++) {
            int tid;
            double suma;
            synchronized (ATMThread.class) {
                tid  = nextTranzactieId++;
                suma = 100 + tid * 50;
            }
            Tranzactie t = new Tranzactie(tid, suma, "2024-05-0" + (atmId));
            System.out.printf(Locale.US, "[%s] trimite: Tranzactie #%d %.2f RON%n", name, t.id, t.suma);
            try {
                coada.adauga(t, name);
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
