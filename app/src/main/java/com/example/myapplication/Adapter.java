package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;

import io.realm.Realm;
import io.realm.RealmResults;

public class Adapter extends RecyclerView.Adapter<Adapter.TheViewHolder>{

    Context context;

    public Adapter(Context context, RealmResults<Note> noteList) {
        this.context = context;
        this.noteList = noteList;
    }

    RealmResults<Note> noteList;

    @NonNull
    @Override
    public TheViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TheViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TheViewHolder holder, int position) {

        Note note = noteList.get(position);

        holder.titleOutput.setText(note.getTitle());
        holder.textOutput.setText(note.getText());

        String formatedTime = DateFormat.getDateTimeInstance().format(note.createdTime);
        holder.timeOutput.setText(formatedTime);
        holder.image.setImageResource(R.drawable.notelist);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                PopupMenu menu = new PopupMenu(context,v);
                menu.getMenu().add("Delete");
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        if(item.getTitle().equals("Delete")){
                            //delete note
                            Realm realm = Realm.getDefaultInstance();
                            realm.beginTransaction();
                            note.deleteFromRealm();
                            realm.commitTransaction();
                            Toast.makeText(context,"Note deleted",Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    }
                });
                menu.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class TheViewHolder extends RecyclerView.ViewHolder {

        TextView titleOutput, textOutput, timeOutput;
        ImageView image;

        public TheViewHolder(@NonNull View itemView) {
            super(itemView);
            titleOutput = itemView.findViewById(R.id.titleItem);
            textOutput = itemView.findViewById(R.id.textInput);
            timeOutput = itemView.findViewById(R.id.timeOutput);
            image      = itemView.findViewById(R.id.notelist);
        }

    }
}
