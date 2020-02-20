package ir.mpkmro.testsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //EDT
    EditText edt_name;
    EditText edt_family;
    EditText edt_id;

    String mID;
    String mName;
    String mFamily;

    //BTN
    Button btn_insert;
    Button btn_delete;
    Button btn_update;
    Button btn_view;

    //DB
    DatabaseManager dbm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();
        dbm = new DatabaseManager(this);


        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });


        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData();
            }
        });

    }

    private void showData() {
        String vID = edt_id.getText().toString();
        Person vPer = dbm.getPerson(vID);
        edt_name.setText(vPer.pName);
        edt_family.setText(vPer.pFamily);

    }

    private void insertData() {
        getDataFromEDT();
    }

    private void setDataintoModel() {
        Person iperson = new Person();
        iperson.pID =mID;
        iperson.pName = mName;
        iperson.pFamily = mFamily;
        dbm.insertperson(iperson);

        Toast.makeText(this, "دیتا با موفقیت ذخیره شد.", Toast.LENGTH_SHORT).show();
    }

    private void getDataFromEDT() {
        mID = edt_id.getText().toString();
        mName = edt_name.getText().toString();
        mFamily = edt_family.getText().toString();

        if (TextUtils.isEmpty(mID)||TextUtils.isEmpty(mName)||TextUtils.isEmpty(mFamily)) {
            Toast.makeText(this, "لطفا تمام فیلدها را حل کنید!", Toast.LENGTH_SHORT).show();
        }else {
            setDataintoModel();
        }

    }

    private void setupViews() {
        edt_name = findViewById(R.id.edt_name);
        edt_family = findViewById(R.id.edt_family);
        edt_id = findViewById(R.id.edt_id);
        btn_delete = findViewById(R.id.btn_delete);
        btn_insert = findViewById(R.id.btn_insert);
        btn_update = findViewById(R.id.btn_update);
        btn_view = findViewById(R.id.btn_view);
    }




}
