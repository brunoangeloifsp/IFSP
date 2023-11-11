package br.edu.ifsp.aulasqlite.view;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.edu.ifsp.aulasqlite.R;
import br.edu.ifsp.aulasqlite.model.entity.Pessoa;

public class ListPessoasRecyclerViewAdapter extends
        RecyclerView.Adapter<ListPessoasRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Pessoa> mArray;
    public PessoasListener listener = null;
    public PessoasLongListener longListener = null;

    public ListPessoasRecyclerViewAdapter(ArrayList<Pessoa> a) {
        this.mArray = a;
    }

    interface PessoasListener {
        void onItemClick(Integer pos);
    }

    interface PessoasLongListener {
        void onItemLongClick(Integer pos);
    }

    public void setClickListener(PessoasListener _listener) {
        this.listener = _listener;
    }

    public void setLongClickListener(PessoasLongListener _longListener) {
        this.longListener = _longListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pessoa_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pessoa p = mArray.get(position);
        holder.getTextView().setText(p.getNome());
    }

    @Override
    public int getItemCount() {
        return mArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ViewHolder(View view) {
            super(view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(getAdapterPosition());
                }
            });

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    longListener.onItemLongClick(getAdapterPosition());
                    return true;
                }
            });

            textView = view.findViewById(R.id.tvNomePessoa);
            textView.setTextColor(Color.parseColor("#FFFFFF"));
        }

        public TextView getTextView(){
            return this.textView;
        }
    }
}
