package com.example.liujiachao.zhihudaily.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.liujiachao.zhihudaily.R;
import com.example.liujiachao.zhihudaily.entity.Comment;
import com.example.liujiachao.zhihudaily.entity.Comments;
import com.example.liujiachao.zhihudaily.utils.Dater;

import java.text.DecimalFormat;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by liujiachao on 2016/9/13.
 */
public class CommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Comment> comments;
    private Context context;

    public CommentAdapter(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.comment_content_item,parent,false);
        return new ItemViewHolder(view);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder viewHolder = (ItemViewHolder)holder;
        Comment comment = comments.get(position);
        viewHolder.nickname.setText(comment.getAuthor());
        Glide.with(context).load(comment.getAvatar()).diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade().into(viewHolder.ivAvatar);
        viewHolder.pubTime.setText(Dater.parseTime(comment.getTime()));
        if (comment.isVoted()) {
            viewHolder.vote.setCompoundDrawablesRelativeWithIntrinsicBounds(context.getResources().getDrawable(R.drawable.comment_voted),null,null,null);
        } else {
            viewHolder.vote.setCompoundDrawablesRelativeWithIntrinsicBounds(context.getResources().getDrawable(R.drawable.comment_vote),null,null,null);
        }
        String vote_num =  comment.getLikes() > 1000 ? new DecimalFormat("#.0")
                .format(((double)comment.getLikes())/1000) + "K" : comment.getLikes() + "";
        viewHolder.vote.setText(vote_num);
        viewHolder.commentContent.setText(comment.getContent());

        if (comment.getReply_to() != null) {

        }


    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView nickname;
        TextView vote;
        TextView commentContent;
        TextView replyContent;
        TextView pubTime;
        TextView expand;
        ImageView ivAvatar;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ivAvatar = (ImageView)itemView.findViewById(R.id.iv_comment_avatar);
            nickname = (TextView)itemView.findViewById(R.id.tv_comment_nickname);
            vote = (TextView)itemView.findViewById(R.id.tv_comment_vote);
            commentContent = (TextView)itemView.findViewById(R.id.tv_comment_content);
            replyContent = (TextView)itemView.findViewById(R.id.tv_reply_comment_content);
            pubTime = (TextView)itemView.findViewById(R.id.tv_comment_time);
            expand = (TextView)itemView.findViewById(R.id.tv_comment_expand_or_pack);
        }
    }
}
