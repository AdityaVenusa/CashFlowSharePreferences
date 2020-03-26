package com.example.cashflowsharedpreferences.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cashflowsharedpreferences.MainActivity;
import com.example.cashflowsharedpreferences.R;
import com.example.cashflowsharedpreferences.models.Transaction;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
    private List<Transaction> items;
    private OnItemTransactionListener listener;

    Locale lokal = new Locale("in", "ID");
    NumberFormat rupiah = NumberFormat.getCurrencyInstance(lokal);

    public TransactionAdapter(List<Transaction> transactions, MainActivity listener) {
    }

    public interface OnItemTransactionListener {
        void onTransactionClicked(int index, Transaction item);
    }
    public TransactionAdapter(List<Transaction> items, OnItemTransactionListener listener) {
        this.items = items;
        this.listener = listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_transaction, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transaction item = items.get(position);
        holder.bind(position, item);
    }

    @Override
    public int getItemCount() {
        return (items != null) ? items.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView descriptionText;
        TextView amountText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            descriptionText = itemView.findViewById(R.id.text_description);
            amountText = itemView.findViewById(R.id.text_amount);
        }

        public void bind(final int index, final Transaction item) {
            descriptionText.setText(item.getDescription());
            //pembeda credit & debit
            if (item.getType == Transaction.Type.DEBIT){
                amountText.setText("Debit: "+ rupiah.format(item.getAmount()));
                amountText.setTextColor(Color.parseColor("#DD1212"));
            }else {
                amountText.setText("Credit: "+rupiah.format(item.getAmount()));
                amountText.setTextColor(Color.parseColor("#20AD26"));
            }

            // TODO: Tambahkan interaksi click di sini
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onTransactionClicked(index, item);
                }
            });
        }
    }
}
