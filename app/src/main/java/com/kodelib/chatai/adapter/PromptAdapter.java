package com.kodelib.chatai.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kodelib.chatai.R;
import com.kodelib.chatai.activity.ChatActivity;
import com.kodelib.chatai.models.Assistant;
import com.kodelib.chatai.utils.Utils;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PromptAdapter extends RecyclerView.Adapter<PromptAdapter.ViewHolder> {

    Activity activity;
    ArrayList<Assistant> list;

    public PromptAdapter(Activity activity, ArrayList<Assistant> list) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_prompt, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Assistant assistant = list.get(position);

        holder.imageView.setImageResource(assistant.getImage());
        holder.tvName.setText(assistant.getName());
        holder.tvContent.setText(assistant.getContent());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.isStart = false;
                Utils.isPrompt = true;

                Intent intent = new Intent(activity, ChatActivity.class);
                intent.putExtra("name", assistant.getName());
                intent.putExtra("category", assistant.getCategory());
                intent.putExtra("image", assistant.getImage());
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tvName, tvContent;


        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.ivImage);
            tvName = itemView.findViewById(R.id.tvName);
            tvContent = itemView.findViewById(R.id.tvContent);

        }
    }

}
