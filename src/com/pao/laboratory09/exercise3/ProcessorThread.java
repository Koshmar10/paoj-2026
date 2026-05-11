package com.pao.laboratory09.exercise3;

import java.util.Locale;

public class ProcessorThread implements Runnable {
    volatile boolean activ = true;
    private final CoadaTranzactii coada;

    public ProcessorThread(CoadaTranzactii coada) {
        this.coada = coada;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Tranzactie t = coada.extrage(); 
                if (t == null) break;
                System.out.printf(Locale.US, "[Processor] Factura #%d - %.2f RON | %s%n",
                        t.id, t.suma, t.data);
                Thread.sleep(80);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        activ = false;
    }
}
