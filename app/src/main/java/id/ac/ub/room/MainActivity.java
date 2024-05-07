package id.ac.ub.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button bt1;
    Button bt2;
    EditText et1;
    RecyclerView rv1;
    private AppDatabase appDb;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appDb = AppDatabase.getInstance(getApplicationContext());
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        rv1 = findViewById(R.id.rv1);
        et1 = findViewById(R.id.et1);
        List<Item> list = new ArrayList<>();
        Item item = new Item();
        item.setId(new Random().nextInt(789));
        item.setJudul(et1.getText().toString());
        list.add(item);
        Adapter adapter = new Adapter(this, list);
        rv1.setAdapter(adapter);
        rv1.setLayoutManager(new LinearLayoutManager(this));

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                Item item = new Item();
                item.setId(new Random().nextInt(10000));
                item.setJudul(et1.getText().toString());
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        appDb.itemDao().insertAll(item);
                        List<Item> list = appDb.itemDao().getAll();
                        String s = "";
                        for (Item item : list) {
                            s = s + item.getJudul() + '\n';
                        }
                        Log.d("logbro", s);
                    }
                });
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        List<Item> templist = appDb.itemDao().getAll();
                        list.clear();
                        list.addAll(templist);
                    }
                });
                adapter.notifyDataSetChanged();
            }
        });
    }
}