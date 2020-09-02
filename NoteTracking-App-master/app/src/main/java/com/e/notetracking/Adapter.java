package com.example.notetracking;

import android.content.Context;
import android.service.autofill.TextValueSanitizer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    LayoutInflater inflater;
    List<Notes> notess;

    Adapter(Context context, List<Notes> notess){
        this.inflater = LayoutInflater.from(context);
        this.notess = notess;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = inflater.inflate(R.layout.custom_list_view, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int i) {
        String subject = notess.get(i).getSubject();
        String date = notess.get(i).getDate();
        String time = notess.get(i).getTime();
        Log.d("Subject","onBindViewHolder: Subject ->" +subject);

        holder.noteSubject.setText(subject);
        holder.noteDate.setText(date);
        holder.noteTime.setText(time);
    }
    @Override
    public int getItemCount() {

        return notess.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView noteSubject,noteDate,noteTime;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            noteSubject = itemView.findViewById(R.id.noteSubject);
            noteDate = itemView.findViewById(R.id.noteDate);
            noteTime = itemView.findViewById(R.id.noteTime);

        }
    }
}
