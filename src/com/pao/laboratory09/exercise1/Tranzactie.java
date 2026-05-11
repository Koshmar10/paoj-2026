package com.pao.laboratory09.exercise1;

import java.io.Serializable;

public class Tranzactie implements Serializable {
    static final long serialVersionUID = 1L;
    int id;
    double suma;
    String data;
    String contSursa;
    String contDestinatie;
    TipTranzactie tip;
    transient String note;
}