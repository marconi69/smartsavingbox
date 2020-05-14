package com.example.takotlin;

public class History

{
    String username;
    String tanggaldanwaktu;
    String status;

    public History(String username, String tanggaldanwaktu, String status) {
        this.username = username;
        this.tanggaldanwaktu = tanggaldanwaktu;
        this.status = status;
    }

    @Override
    public String toString() {
        return "History{" +
                "username='" + username + '\'' +
                ", tanggaldanwaktu='" + tanggaldanwaktu + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTanggaldanwaktu() {
        return tanggaldanwaktu;
    }

    public void setTanggaldanwaktu(String tanggaldanwaktu) {
        this.tanggaldanwaktu = tanggaldanwaktu;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

