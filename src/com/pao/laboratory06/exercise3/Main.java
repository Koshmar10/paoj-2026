package com.pao.laboratory06.exercise3;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Inginer[] ingineri = {
            new Inginer("Zamfir", "Ion", "0721000001", 8000),
            new Inginer("Andrei", "Maria", "0721000002", 12000),
            new Inginer("Popescu", "Dan", null, 9500),
        };

        System.out.println("-- Sortare naturală (după nume) --");
        Arrays.sort(ingineri);
        for (Inginer ing : ingineri)
            System.out.println(ing.getNume() + " " + ing.getPrenume() + " - " + ing.getSalariu());

        System.out.println("-- Sortare după salariu descrescător --");
        Arrays.sort(ingineri, new ComparatorInginerSalariu());
        for (Inginer ing : ingineri)
            System.out.println(ing.getNume() + " " + ing.getPrenume() + " - " + ing.getSalariu());

        PlataOnline contInginer = new Inginer("Test", "User", "0700000000", 5000);
        contInginer.autentificare("test", "pass");
        System.out.println("\n-- Acces prin PlataOnline --");
        System.out.println("Sold: " + contInginer.consultareSold());
        System.out.println("Plată 1000: " + contInginer.efectuarePlata(1000));
        System.out.println("Sold după plată: " + contInginer.consultareSold());

        System.out.println("\n-- PersoanaJuridica cu telefon valid --");
        PlataOnlineSMS pjCuTelefon = new PersoanaJuridica("TechSRL", "SRL", "0722111222", 50000);
        System.out.println("SMS trimis: " + pjCuTelefon.trimiteSMS("Factura 1234"));
        System.out.println("SMS gol (false așteptat): " + pjCuTelefon.trimiteSMS(""));
        System.out.println("SMS-uri stocate: " + ((PersoanaJuridica) pjCuTelefon).getSmsTrimise());

        System.out.println("\n-- PersoanaJuridica fără telefon --");
        PlataOnlineSMS pjFaraTelefon = new PersoanaJuridica("MicroSRL", "SRL", null, 10000);
        System.out.println("SMS fără telefon (false așteptat): " + pjFaraTelefon.trimiteSMS("Mesaj"));

        System.out.println("\n-- Constante financiare --");
        System.out.println("TVA: " + ConstanteFinanciare.TVA.getValoare());
        System.out.println("Salariu minim: " + ConstanteFinanciare.SALARIU_MINIM.getValoare());
        System.out.println("Cotă impozit: " + ConstanteFinanciare.COTA_IMPOZIT.getValoare());

        System.out.println("\n-- Tratare erori --");

        try {
            contInginer.autentificare(null, "parola");
        } catch (IllegalArgumentException e) {
            System.out.println("Eroare autentificare user null: " + e.getMessage());
        }

        try {
            PlataOnline ing = new Inginer("X", "Y", "0700", 1000);
            if (!(ing instanceof PlataOnlineSMS)) {
                throw new UnsupportedOperationException("Inginer nu are capabilitate SMS.");
            }
        } catch (UnsupportedOperationException e) {
            System.out.println("Eroare SMS pe entitate greșită: " + e.getMessage());
        }

        try {
            contInginer.efectuarePlata(-100);
        } catch (IllegalArgumentException e) {
            System.out.println("Eroare plată sumă negativă: " + e.getMessage());
        }
    }
}
