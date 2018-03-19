package jadwalbola.triwi.id.jadwalbola;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class DisplayList extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerciew);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        BolaDbHelper bolaDbHelper = new BolaDbHelper(this);
        SQLiteDatabase sqLiteDatabase = bolaDbHelper.getReadableDatabase();

        Cursor cursor = bolaDbHelper.getInformation(sqLiteDatabase);

        cursor.moveToFirst();
        do {
            Bola bola = new Bola(cursor.getString(0),cursor.getString(1), cursor.getString(2));
            arrayList.add(bola);

        }while(cursor.moveToNext());

        adapter = new RecyclerAdapter(arrayList);
        recyclerView.setAdapter(adapter);
    }
}
