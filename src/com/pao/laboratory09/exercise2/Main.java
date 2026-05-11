package com.pao.laboratory09.exercise2;

import com.pao.laboratory09.exercise1.TipTranzactie;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.*;

public class Main {
    private static final String OUTPUT_FILE = "output/lab09_ex2.bin";
    private static final int RECORD_SIZE = 32;

    private static final String[] STATUS_NAMES = {"PENDING", "PROCESSED", "REJECTED"};

    private static byte[] encodeRecord(int id, double suma, String data, TipTranzactie tip) {
        byte[] rec = new byte[RECORD_SIZE];
        ByteBuffer buf = ByteBuffer.wrap(rec).order(ByteOrder.LITTLE_ENDIAN);
        buf.putInt(id);                          // bytes 0-3
        buf.putDouble(suma);                     // bytes 4-11
        byte[] dataBytes = String.format("%-10s", data).getBytes();
        System.arraycopy(dataBytes, 0, rec, 12, 10); // bytes 12-21
        rec[22] = (byte) (tip == TipTranzactie.CREDIT ? 0 : 1); // byte 22
        rec[23] = 0;                             
        return rec;
    }

    private static String formatRecord(int idx, byte[] rec) {
        ByteBuffer buf = ByteBuffer.wrap(rec).order(ByteOrder.LITTLE_ENDIAN);
        int id       = buf.getInt(0);
        double suma  = buf.getDouble(4);
        String data  = new String(rec, 12, 10).trim();
        String tip   = rec[22] == 0 ? "CREDIT" : "DEBIT";
        String status = STATUS_NAMES[rec[23]];
        return String.format(Locale.US, "[%d] id=%d data=%s tip=%s suma=%.2f RON status=%s",
                idx, id, data, tip, suma, status);
    }

    public static void main(String[] args) throws Exception {
        // 1. Citește N tranzacții din stdin
        try (Scanner in = new Scanner(System.in)) {
            int n = Integer.parseInt(in.nextLine().trim());
            int[] ids = new int[n];
            double[] sume = new double[n];
            String[] date = new String[n];
            TipTranzactie[] tipuri = new TipTranzactie[n];

            for (int i = 0; i < n; i++) {
                String[] parts = in.nextLine().trim().split(" ");
                ids[i]    = Integer.parseInt(parts[0]);
                sume[i]   = Double.parseDouble(parts[1]);
                date[i]   = parts[2];
                tipuri[i] = TipTranzactie.valueOf(parts[3]);
            }

            try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(OUTPUT_FILE))) {
                for (int i = 0; i < n; i++) {
                    dos.write(encodeRecord(ids[i], sume[i], date[i], tipuri[i]));
                }
            }

            try (RandomAccessFile raf = new RandomAccessFile(OUTPUT_FILE, "rw")) {
                while (in.hasNextLine()) {
                    String[] cmd = in.nextLine().trim().split(" ");
                    switch (cmd[0]) {
                        case "READ" -> {
                            int idx = Integer.parseInt(cmd[1]);
                            byte[] rec = new byte[RECORD_SIZE];
                            raf.seek((long) idx * RECORD_SIZE);
                            raf.readFully(rec);
                            System.out.println(formatRecord(idx, rec));
                        }
                        case "UPDATE" -> {
                            int idx = Integer.parseInt(cmd[1]);
                            String statusName = cmd[2];
                            byte statusByte = (byte) Arrays.asList(STATUS_NAMES).indexOf(statusName);
                            raf.seek((long) idx * RECORD_SIZE + 23);
                            raf.write(statusByte);
                            System.out.printf("Updated [%d]: %s%n", idx, statusName);
                        }
                        case "PRINT_ALL" -> {
                            for (int idx = 0; idx < n; idx++) {
                                byte[] rec = new byte[RECORD_SIZE];
                                raf.seek((long) idx * RECORD_SIZE);
                                raf.readFully(rec);
                                System.out.println(formatRecord(idx, rec));
                            }
                        }
                    }
                }
            }
        }
    }
}
