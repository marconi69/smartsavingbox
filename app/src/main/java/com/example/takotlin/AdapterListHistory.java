package com.example.takotlin;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class AdapterListHistory {
    public String username;
    public String tanggaldanwaktu;

    public AdapterListHistory() {
    }

    public AdapterListHistory(String username, String tanggaldanwaktu) {
        this.username = username;
        this.tanggaldanwaktu = tanggaldanwaktu;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("username", username);
        result.put("tanggaldanwaktu", tanggaldanwaktu);
        return result;
    }
}
