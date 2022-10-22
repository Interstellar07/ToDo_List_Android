package com.zaidisam.to_do;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;
import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter <Adapter.viewholder>{
    private DatabaseReference tododata;


   Context context;
   ArrayList<ToDoModel> arrayList;
   public String id;

     Adapter(Context context , ArrayList<ToDoModel> arrayList)
     {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.todolayout,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

         holder.wastetype.setText("Waste Type: "+ arrayList.get(position).wastetype);
         holder.date.setText("Date: "+arrayList.get(position).data);
         holder.location.setText("Location: "+arrayList.get(position).location);
         holder.status.setText("Status: Not Disposed");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView type;
                TextView nature;
                TextView weight;
                TextView date;
                TextView location;
                ImageView image;
                Button share;
                AlertDialog.Builder myDialog = new AlertDialog.Builder(context);
                LayoutInflater inflater = LayoutInflater.from(context);
                View myView = inflater.inflate(R.layout.expandedview,null);
                type = myView.findViewById(R.id.type);
                share = myView.findViewById(R.id.share);
                share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       // Uri uri = Uri.parse("smsto:" + );
                        String w= "Sameer";
                        Intent i = new Intent(Intent.ACTION_SENDTO);
                        i.setType("text/plain").putExtra(Intent.EXTRA_TEXT,w);
                        i.setPackage("com.whatsapp");
                        startActivity(context,i,null);
                    }
                });
                type.setText("Type: "+arrayList.get(position).wastetype);
                nature = myView.findViewById(R.id.Nature);
                nature.setText("Nature: "+arrayList.get(position).wastenature);
                weight = myView.findViewById(R.id.Weight);
                weight.setText("Weight: "+arrayList.get(position).amountwaste);
                date = myView.findViewById(R.id.Date);
                date.setText("Date: "+arrayList.get(position).data.toString());
                location = myView.findViewById(R.id.Location);
                location.setText("Location: "+arrayList.get(position).location);
                image = myView.findViewById(R.id.wasteImage);
                Picasso.get()
                        .load(arrayList.get(position).imgurl)
                        .into(image);
                myDialog.setView(myView);
                final AlertDialog dialog = myDialog.create();
                myDialog.show();
                dialog.setCancelable(false);
            }
        });

        Picasso.get()
                .load(arrayList.get(position).imgurl)
                .into(holder.wstimg);
        System.out.println("AAAAAH"+arrayList.get(position).imgurl);




    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class viewholder extends RecyclerView.ViewHolder {
        TextView wastetype,location,date,status;
        ImageView wstimg;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            wstimg = itemView.findViewById(R.id.imageview);
           wastetype = itemView.findViewById(R.id.Rtvwsttype);
            date = itemView.findViewById(R.id.date);
            location = itemView.findViewById(R.id.location);
            status = itemView.findViewById(R.id.status);

        }
    }
}
