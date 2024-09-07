package com.example.mynotes;

import android.arch.persistence.room.processor.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerNotesAdapter  extends RecyclerView.Adapter<RecyclerNotesAdapter.ViewHolder> {
    MainActivity context;
    ArrayList<Note> arrNotes=new ArrayList<>();

    public RecyclerNotesAdapter(MainActivity context, ArrayList<Note> arrNotes) {
        this.context = context;
        this.arrNotes = arrNotes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.note_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTitle.setText(arrNotes.get(position).getTitle());
        holder.txtContent.setText(arrNotes.get(position).getContent());


    }

    @Override
    public int getItemCount() {
        return arrNotes.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTitle,txtContent;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtTitle=itemView.findViewById(R.id.txtTitle);
            txtContent=itemView.findViewById(R.id.txtContent);
        }
    }
}
