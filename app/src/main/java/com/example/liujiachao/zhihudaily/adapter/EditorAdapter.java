package com.example.liujiachao.zhihudaily.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.liujiachao.zhihudaily.R;
import com.example.liujiachao.zhihudaily.entity.Edit;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by liujiachao on 2016/9/8.
 */
public class EditorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<Edit> edits = new ArrayList<>();

    public EditorAdapter(List<Edit> edits) {
        this.edits = edits;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.editor_list_item,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Context context = holder.itemView.getContext();
        ItemViewHolder viewHolder = (ItemViewHolder)holder;

        if (edits == null && edits.size() == 0) {
            viewHolder.tvName.setText("好多人");
            viewHolder.editImg.setImageResource(R.drawable.menu_avatar);
            viewHolder.tvBio.setVisibility(View.GONE);
        } else {
            Edit edit = edits.get(position);

            viewHolder.tvName.setText(edit.getName());
            viewHolder.tvBio.setText(edit.getBio());
            Glide.with(context).load(edit.getAvatar()).diskCacheStrategy(DiskCacheStrategy.ALL)
                    .crossFade()
                    .into(viewHolder.editImg);
        }

    }

    @Override
    public int getItemCount() {
        return edits.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView editImg;
        TextView tvName;
        TextView tvBio;

        public ItemViewHolder(View itemView) {
            super(itemView);
            editImg = (ImageView)itemView.findViewById(R.id.edit_img);
            tvName = (TextView)itemView.findViewById(R.id.edit_name);
            tvBio = (TextView)itemView.findViewById(R.id.edit_bio);
        }
    }
}
