package com.example.laba3_1genba;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AddRecordActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private EditText inputFIO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        dbHelper = new DatabaseHelper(this);
        inputFIO = findViewById(R.id.input_fio);
    }

    public void saveRecord(View view) {
        String fio = inputFIO.getText().toString().trim();

        if (!fio.isEmpty()) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.COLUMN_FIO, fio);

            long newRowId = db.insert(DatabaseHelper.TABLE_NAME, null, values);

            if (newRowId != -1) {
                setResult(RESULT_OK);
                finish();
            } else {
                setResult(RESULT_CANCELED);
                finish();
            }

            db.close();
        }
    }
}