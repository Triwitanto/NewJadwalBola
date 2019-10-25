package jadwalbola.triwi.id;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;

import jadwalbola.triwi.id.jadwalbola.R;

/**
 * Created by TRW on 3/13/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>{
    ArrayList<Bola> arrayList = new ArrayList<>();
    RecyclerAdapter(ArrayList<Bola> arrayList)
    {
        this.arrayList= arrayList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout,parent,false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Bola bola = arrayList.get(position);
        holder.Tanggal.setText(String.valueOf(bola.getTanggal()));
        holder.Jadwal.setText(String.valueOf(bola.getJadwal()));
        holder.Tv.setText(String.valueOf(bola.getTv()));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder
    { TextView Tanggal, Jadwal, Tv;
        RecyclerViewHolder (View view)
        {
            super(view);
            Tanggal = (TextView) view.findViewById(R.id.tangga);
            Jadwal = (TextView) view.findViewById(R.id.jadwal);
            Tv = (TextView) view.findViewById(R.id.tv);
        }
    }
}