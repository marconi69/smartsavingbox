package com.example.takotlin;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class HistoryActivity extends AppCompatActivity {

    private List<History> historyList = new ArrayList<>();
    private RecyclerView recyclerView;
    private AdapterHistory nAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        recyclerView = findViewById(R.id.container_data);

        initBookData();
    }

    private void initBookData() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("history");
        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get map of users in datasnapshot
                        collectPhoneNumbers((Map<String, Object>) dataSnapshot.getValue());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }

                });
    }

    private void collectPhoneNumbers(Map<String, Object> historys) {

        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : historys.entrySet()) {

            //Get user map
            Map singleHistory = (Map) entry.getValue();
            Log.i("History", singleHistory.toString());
            //Get phone field and append to list
            historyList.add(new History(singleHistory.get("username").toString(), singleHistory.get("tanggaldanwaktu").toString(), singleHistory.get("status").toString()));
        }

        Collections.sort(historyList, new Comparator<History>() {
            @Override
            public int compare(History history1, History history2)
            {

                return  history2.getTanggaldanwaktu().compareTo(history1.getTanggaldanwaktu());
            }
        });

        nAdapter = new AdapterHistory(historyList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(nAdapter);
        nAdapter.notifyDataSetChanged();
    }
}
