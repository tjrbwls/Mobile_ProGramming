package com.example.chap03_s7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    EditText edittext;
    RadioButton rb_g, rb_s;
    CheckBox cb_w, cb_c;
    TextView textview_display;
    Button button_submit;
    String msg;
    private String city[]={"서울", "부산", "대구"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edittext=findViewById(R.id.edit);
        rb_g=findViewById(R.id.radiobutton1);
        rb_s=findViewById(R.id.radiobutton2);
        cb_w=findViewById(R.id.checkbox1);
        cb_c=findViewById(R.id.checkbox2);
        textview_display=findViewById(R.id.textview);
        button_submit=findViewById(R.id.button);
        spinner=findViewById(R.id.spinner);
        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,city);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                display();
            }
            @Override public void onNothingSelected(AdapterView<?> adapterView) {}
        });
        edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                display();
            }
        });
        rb_s.setOnCheckedChangeListener((a,b)->display());
        cb_w.setOnCheckedChangeListener((a,b)->display());
        cb_c.setOnCheckedChangeListener((a,b)->display());
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void display() {
        msg="목적지: "+spinner.getSelectedItem().toString();
        msg+="\n승차인원: "+edittext.getText().toString()+" (명)";
        if(rb_g.isChecked()) msg+="\n좌석: 일반실";
        else msg+="\n좌석: 특실";
        if(cb_w.isChecked()) msg+="\n"+cb_w.getText().toString();
        if(cb_c.isChecked()) msg+="\n"+cb_c.getText().toString();

        textview_display.setText(msg);
    }

}