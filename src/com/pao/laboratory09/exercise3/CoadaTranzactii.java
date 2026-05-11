package com.pao.laboratory09.exercise3;

import java.util.LinkedList;
import java.util.Queue;

public class CoadaTranzactii {
    private final Queue<Tranzactie> coada = new LinkedList<>();
    private final int capacitate;
    private volatile boolean done = false;

    public CoadaTranzactii(int capacitate) {
        this.capacitate = capacitate;
    }

    public synchronized void adauga(Tranzactie t, String atmName) throws InterruptedException {
        while (coada.size() == capacitate) {
            System.out.println("[" + atmName + "] astept loc...");
            wait();
        }
        coada.add(t);
        notifyAll();
    }

    public synchronized Tranzactie extrage() throws InterruptedException {
        while (coada.isEmpty() && !done) {
            wait();
        }
        if (coada.isEmpty()) return null; 
        Tranzactie t = coada.poll();
        notifyAll();
        return t;
    }

    public synchronized void termina() {
        done = true;
        notifyAll(); 
    }
}
