package com.example.ukk2024_nickoikhwanprayogi;

import androidx.appcompat.app.AppCompatActivity;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private int[] tombolNumeric = {R.id.satu,R.id.dua,R.id.tiga,R.id.empat,R.id.lima,R.id.enam,R.id.tujuh,R.id.delapan,R.id.sembilan,R.id.nol};

    private int[] tombolOperator = {R.id.kali,R.id.plus,R.id.bagi,R.id.min};

    private TextView layarTampil;

    private TextView layarHasil;

    private boolean AngkaTerakhir;

    private boolean setelahTitik;

    private boolean kaloError;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.layarTampil = (TextView)findViewById(R.id.tampil);
        this.layarHasil = (TextView) findViewById(R.id.hasil);
        setNumeric();
        setOperator();
    }
    private void setNumeric() {
        View.OnClickListener listener = (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button tbl = (Button) v;
                if(kaloError){
                    layarTampil.setText(tbl.getText());
                }else {
                    layarTampil.append(tbl.getText());
                }
                AngkaTerakhir = true;
            }

        });
        for (int id : tombolNumeric){
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void setOperator() {
        View.OnClickListener listener = (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button tbl = (Button) v;
                if (AngkaTerakhir && !kaloError) {
                    layarTampil.append(tbl.getText());
                    AngkaTerakhir = false;
                    setelahTitik = false;
                }
            }
        });
        for (int id : tombolOperator) {
            findViewById(id).setOnClickListener(listener);
        }

        findViewById(R.id.ttk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AngkaTerakhir && !kaloError && !setelahTitik) {
                    layarTampil.append(".");
                    AngkaTerakhir = false;
                    setelahTitik = false;
                }
            }
        });
        findViewById(R.id.clr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layarTampil.setText("");
                layarHasil.setText("");
                AngkaTerakhir = false;
                setelahTitik = false;
                kaloError = false;
            }
        });

        findViewById(R.id.sd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEqual();
            }

            public void onEqual() {
                if (AngkaTerakhir && !kaloError) {
                    String txt = layarTampil.getText().toString();
                    Expression expression = new ExpressionBuilder(txt).build();

                try {
                    double result = expression.evaluate();
                    layarHasil.setText(Double.toString(result));
                    AngkaTerakhir = true;

                } catch (ArithmeticException ex) {
                    layarHasil.setText("Syntax Error");
                    kaloError = true;
                    setelahTitik = false;
                }
            }
            }

        });
    }
}