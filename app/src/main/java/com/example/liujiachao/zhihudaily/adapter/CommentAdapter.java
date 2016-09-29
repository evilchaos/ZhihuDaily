package com.example.liujiachao.zhihudaily.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.liujiachao.zhihudaily.R;
import com.example.liujiachao.zhihudaily.entity.Comment;
import com.example.liujiachao.zhihudaily.entity.Comments;
import com.example.liujiachao.zhihudaily.entity.Reply;
import com.example.liujiachao.zhihudaily.utils.Dater;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by liujiachao on 2016/9/13.
 */
public class CommentAdapter extends BaseAdapter {

    private Context context;
    private List<Comment> comments = new ArrayList<>();
    public CommentAdapter(Context context,List<Comment> comments) {
        this.context = context;
        this.comments = comments;
    }

    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Object getItem(int position) {
        return comments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null ) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.comment_content_item,null);

            viewHolder.ivAvatar = (ImageView)convertView.findViewById(R.id.iv_comment_avatar);
            viewHolder.nickname = (TextView)convertView.findViewById(R.id.tv_comment_nickname);
            viewHolder.vote = (TextView)convertView.findViewById(R.id.tv_comment_vote);
            viewHolder.commentContent = (TextView)convertView.findViewById(R.id.tv_comment_content);
            viewHolder.replyContent = (TextView)convertView.findViewById(R.id.tv_reply_comment_content);
            viewHolder.pubTime = (TextView)convertView.findViewById(R.id.tv_comment_time);
            viewHolder.expand = (TextView)convertView.findViewById(R.id.tv_comment_expand_or_pack);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }




        return null;
    }



    public class ViewHolder {
        public TextView nickname;
        public TextView vote;
        public TextView commentContent;
        public TextView replyContent;
        public TextView pubTime;
        public TextView expand;
        public ImageView ivAvatar;
    }
    //    private List<Comment> comments = new ArrayList<>();
//    private Context context;
//    private boolean hasGetLineCount = false;
//    private boolean hasMore = false;
//
//    public CommentAdapter(List<Comment> comments) {
//        this.comments = comments;
//    }
//
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        context = parent.getContext();
//        View view = LayoutInflater.from(context).inflate(R.layout.comment_content_item,parent,false);
//        return new ItemViewHolder(view);
//    }
//
//    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
//
//        final ItemViewHolder viewHolder = (ItemViewHolder)holder;
//        final Comment comment = comments.get(position);
//        viewHolder.nickname.setText(comment.getAuthor());
//        Glide.with(context).load(comment.getAvatar()).diskCacheStrategy(DiskCacheStrategy.ALL)
//                .crossFade().into(viewHolder.ivAvatar);
//        viewHolder.pubTime.setText(Dater.parseTime(comment.getTime()));
//        if (comment.isVoted()) {
//            viewHolder.vote.setCompoundDrawablesRelativeWithIntrinsicBounds(context.getResources().getDrawable(R.drawable.comment_voted),null,null,null);
//        } else {
//            viewHolder.vote.setCompoundDrawablesRelativeWithIntrinsicBounds(context.getResources().getDrawable(R.drawable.comment_vote),null,null,null);
//        }
//        String vote_num =  comment.getLikes() > 1000 ? new DecimalFormat("#.0")
//                .format(((double)comment.getLikes())/1000) + "K" : comment.getLikes() + "";
//        viewHolder.vote.setText(vote_num);
//        viewHolder.commentContent.setText(comment.getContent());
//
//        Reply reply = comment.getReply_to();
//        if (reply != null) {
//            if (!TextUtils.isEmpty(reply.getAuthor()) || !TextUtils.isEmpty(reply.getContent())) {
//                viewHolder.replyContent.setText("抱歉，原点评已被作者删除");
//                viewHolder.replyContent.setTextColor(context.getResources().getColor(R.color.text_gray));
//                viewHolder.replyContent.setBackgroundColor(context.getResources().getColor(R.color.background));
//            } else {
//                SpannableStringBuilder ssBuilder = new SpannableStringBuilder("//" + reply.getAuthor() + ": " + reply.getContent());
//                ssBuilder.setSpan(new StyleSpan(Typeface.BOLD),0,reply.getAuthor().length() + 2, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//                ssBuilder.setSpan(new ForegroundColorSpan(Color.BLACK),0,reply.getAuthor().length() + 2,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//                ssBuilder.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.text_gray))
//                        ,reply.getAuthor().length() + 3,ssBuilder.length() - 1
//                        ,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//                viewHolder.replyContent.setText(ssBuilder);
//
//                viewHolder.replyContent.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//                    @Override
//                    public boolean onPreDraw() {
//                        if (!hasGetLineCount) {
//                            hasMore = viewHolder.replyContent.getLineCount() > 2 ;
//                            hasGetLineCount = true;
//                        }
//                        viewHolder.expand.setVisibility(hasMore ?View.VISIBLE:View.GONE);
//                        return true;
//                    }
//                });
//
//                if (hasMore) {
//                    viewHolder.expand.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            if (((TextView)v).getText().toString().equals("展开")) {
//                                viewHolder.expand.setText("收起");
//                                viewHolder.replyContent.setMaxLines(Integer.MAX_VALUE);
//                            } else if (((TextView)v).getText().toString().equals("收起")) {
//                                viewHolder.expand.setText("展开");
//                                viewHolder.replyContent.setMaxLines(2);
//                            }
//
//                        }
//                    });
//                }
//            }
//        }
//
//        viewHolder.vote.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!(comment.isVoted())) {
//                    comment.setVoted(true);
//                    comment.setLikes(comment.getLikes() + 1);
//                } else {
//                    comment.setLikes(comment.getLikes() - 1);
//                    comment.setVoted(false);
//                }
//                notifyItemChanged(position);
//            }
//        });
//    }
//
//    public void updateData(List<Comment> commentList) {
//        comments.clear();
//        comments.addAll(commentList);
//        notifyDataSetChanged();
//    }
//
//    @Override
//    public int getItemCount() {
//        return comments.size();
//    }
//
//    public class ItemViewHolder extends RecyclerView.ViewHolder {
//        TextView nickname;
//        TextView vote;
//        TextView commentContent;
//        TextView replyContent;
//        TextView pubTime;
//        TextView expand;
//        ImageView ivAvatar;
//
//        public ItemViewHolder(View itemView) {
//            super(itemView);
//            ivAvatar = (ImageView)itemView.findViewById(R.id.iv_comment_avatar);
//            nickname = (TextView)itemView.findViewById(R.id.tv_comment_nickname);
//            vote = (TextView)itemView.findViewById(R.id.tv_comment_vote);
//            commentContent = (TextView)itemView.findViewById(R.id.tv_comment_content);
//            replyContent = (TextView)itemView.findViewById(R.id.tv_reply_comment_content);
//            pubTime = (TextView)itemView.findViewById(R.id.tv_comment_time);
//            expand = (TextView)itemView.findViewById(R.id.tv_comment_expand_or_pack);
//        }
//    }
}
