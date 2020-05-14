package com.example.takotlin;

import java.io.Serializable;

public class User{

    String nama;
    String waktu;
    String noHP;

    public User(){

    }

    public User(String nama, String waktu, String noHP) {
        this.nama = nama;
        this.waktu = waktu;
        this.noHP = noHP;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getNoHP() {
        return noHP;
    }

    public void setNoHP(String noHP) {
        this.noHP = noHP;
    }
}
