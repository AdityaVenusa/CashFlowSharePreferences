package com.example.cashflowsharedpreferences;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cashflowsharedpreferences.models.Transaction;

public class SaveActivity extends AppCompatActivity {


    private EditText descriptionInput;
    private EditText amountInput;
    private RadioGroup typeRadioGroup;
    private Transaction item;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        descriptionInput = findViewById(R.id.input_description);
        amountInput = findViewById(R.id.input_amount);
        typeRadioGroup = findViewById(R.id.group_type);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            item = extras.getParcelable(MainActivity.TRANSACTION_KEY);
            index = extras.getInt(MainActivity.INDEX_KEY, 0);
            descriptionInput.setText(item.getDescription());
            amountInput.setText(String.valueOf(item.getAmount()));

            if (item.getType() == Transaction.Type.DEBIT) {
                typeRadioGroup.check(R.id.radio_debit);
            } else if (item.getType() == Transaction.Type.CREDIT) {
                typeRadioGroup.check(R.id.radio_credit);
            }
        }
    }

    private Transaction.Type getCheckedType() {
        if (typeRadioGroup.getCheckedRadioButtonId() == R.id.radio_debit) {
            return Transaction.Type.DEBIT;
        } else if (typeRadioGroup.getCheckedRadioButtonId() == R.id.radio_credit) {
            return Transaction.Type.CREDIT;
        }
        return Transaction.Type.EMPTY;
    }

    public void handleSubmit(View view) {
        int eror = 3;
        if(typeRadioGroup.getCheckedRadioButtonId() == -1){
            Toast.makeText(this, "Jenis tidak boleh kosong", Toast.LENGTH_SHORT).show();
            if(descriptionInput.getText().toString().isEmpty()){
                descriptionInput.setError("Deskripsi tidak boleh kosong");
                if(amountInput.getText().toString().isEmpty() || amountInput.getText().toString().equals("0")){
                    amountInput.setError("Nominal tidak boleh kosong");
                }else{
                    eror -=1;
                }
            }else{
                eror -= 1;
            }
        }else {
            // elsenya jenis kas
            String description = descriptionInput.getText().toString();
            int amount = Integer.parseInt(amountInput.getText().toString());
            Transaction.Type type = getCheckedType();

            item.setDescription(description);
            item.setAmount(amount);
            item.setType(type);

            Intent intent = new Intent();
            intent.putExtra(MainActivity.TRANSACTION_KEY, item);
            intent.putExtra(MainActivity.INDEX_KEY, index);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
