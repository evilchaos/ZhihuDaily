package com.example.liujiachao.zhihudaily.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.liujiachao.zhihudaily.R;
import com.example.liujiachao.zhihudaily.adapter.CommentAdapter;
import com.example.liujiachao.zhihudaily.entity.Comment;
import com.example.liujiachao.zhihudaily.entity.Comments;
import com.example.liujiachao.zhihudaily.entity.Reply;
import com.example.liujiachao.zhihudaily.mvp.presenter.LongCommentsPresenter;
import com.example.liujiachao.zhihudaily.mvp.presenter.ShortCommentsPresenter;
import com.example.liujiachao.zhihudaily.mvp.view.CommentsView;
import com.example.liujiachao.zhihudaily.utils.API;
import com.example.liujiachao.zhihudaily.utils.Dater;
import com.example.liujiachao.zhihudaily.utils.UIHelper;
import com.example.liujiachao.zhihudaily.widgets.MyListView;
import com.example.liujiachao.zhihudaily.widgets.NestedListView;
import com.example.liujiachao.zhihudaily.widgets.VerticalScrollView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liujiachao on 2016/9/12.
 */
public class CommentActivity extends AppCompatActivity implements CommentsView {

    public final static int LONG_COMM_MSG = 0;
    public final static int SHORT_COMM_MSG = 1;
    private final static int maxDescripLine = 2;
    private ScrollView scrollView;
    private TextView longCommentNum;
    private TextView shortCommentNum;
    private LinearLayout longCommLayout;
    private LinearLayout shortCommLayout;
    private Toolbar toolbar;
    private boolean isExpandable = false;

    private int story_id;
    private int long_comments_num;
    private int short_comments_num;
    private int total_comments;
    private Handler mCommentHandler;


    private ShortCommentsPresenter shortCommentsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();

        Intent intent = getIntent();
        story_id = intent.getIntExtra("story_id", 0);
        initToolbar();
        setCommentTextView(intent);
        receiveData();

        shortCommentsPresenter = new ShortCommentsPresenter(this);
        LongCommentsPresenter longCommentsPresenter = new LongCommentsPresenter(this);
        longCommentsPresenter.loadLongComments(story_id);


        if (short_comments_num > 0 ){
            shortCommentNum.setOnClickListener(new View.OnClickListener() {
                @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
                @Override
                public void onClick(View v) {
                    if (!isExpandable) {
                        isExpandable = true;
                        shortCommentNum.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null,
                                getResources().getDrawable(R.drawable.comment_icon_expand), null);
                        shortCommentsPresenter.loadShortComments(story_id);
                    } else if (short_comments_num > 0){
                        isExpandable = false;
                        shortCommLayout.removeAllViews();
                        scrollView.smoothScrollTo(0,0);
                    }

                }
            });
        }

    }

    private void receiveData() {
        mCommentHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case LONG_COMM_MSG:
                        //数据处理逻辑
                        Comments long_comments = (Comments)msg.getData().getSerializable("comments");
                        List<Comment> longCommList = long_comments.getComments();
                        if (longCommList.size() > 0 ) {
                            setCommentView(longCommList,longCommLayout);
                        }
                        break;
                    case SHORT_COMM_MSG:
                        Comments short_comments = (Comments)msg.getData().getSerializable("comments");
                        List<Comment> shortCommList = short_comments.getComments();
                        if (shortCommList.size() > 0) {
                            setCommentView(shortCommList,shortCommLayout);
                            scrollView.smoothScrollBy(0, (int) shortCommentNum.getY());
                        }
                        break;
                }
                super.handleMessage(msg);
            }
        };
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void setCommentView(List<Comment> commentList,LinearLayout commLinearLayout) {

        for (Comment comment : commentList) {
            View commentView = LayoutInflater.from(CommentActivity.this).inflate(R.layout.comment_content_item,null);
            ImageView ivAvatar = (ImageView)commentView.findViewById(R.id.iv_comment_avatar);
            TextView nickname = (TextView)commentView.findViewById(R.id.tv_comment_nickname);
            TextView vote = (TextView)commentView.findViewById(R.id.tv_comment_vote);
            TextView commentContent = (TextView)commentView.findViewById(R.id.tv_comment_content);
            final TextView replyContent = (TextView)commentView.findViewById(R.id.tv_reply_comment_content);
            TextView pubTime = (TextView)commentView.findViewById(R.id.tv_comment_time);
            final TextView expand = (TextView)commentView.findViewById(R.id.tv_comment_expand_or_pack);

            nickname.setText(comment.getAuthor());
            pubTime.setText(Dater.parseTime(comment.getTime()));
            Glide.with(CommentActivity.this).load(comment.getAvatar()).diskCacheStrategy(DiskCacheStrategy.ALL)
                    .crossFade().into(ivAvatar);

            if (comment.isVoted()) {
                vote.setCompoundDrawablesRelativeWithIntrinsicBounds(CommentActivity.this.getResources().getDrawable(R.drawable.comment_voted),null,null,null);
            } else {
                vote.setCompoundDrawablesRelativeWithIntrinsicBounds(CommentActivity.this.getResources().getDrawable(R.drawable.comment_vote),null,null,null);
            }

            String vote_num =  comment.getLikes() > 1000 ? new DecimalFormat("#.0")
                    .format(((double)comment.getLikes())/1000) + "K" : comment.getLikes() + "";
            vote.setText(vote_num);
            commentContent.setText(comment.getContent());

            Reply reply = comment.getReply_to();
            if (reply != null) {
                if (TextUtils.isEmpty(reply.getAuthor()) || TextUtils.isEmpty(reply.getContent())) {
                    replyContent.setText("抱歉，原点评已被作者删除");
                    replyContent.setTextColor(CommentActivity.this.getResources().getColor(R.color.text_gray));
                    replyContent.setBackgroundColor(CommentActivity.this.getResources().getColor(R.color.background));
                } else {
                    SpannableStringBuilder ssBuilder = makeSpStringBuilder(reply);
                    replyContent.setText(ssBuilder);
                    replyContent.setHeight(replyContent.getLineHeight() * maxDescripLine);
                    replyContent.post(new Runnable() {
                        @Override
                        public void run() {
                            expand.setVisibility(replyContent.getLineCount() > maxDescripLine ? View.VISIBLE:View.GONE);
                        }
                    });

                    expand.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (((TextView)v).getText().toString().equals("展开")) {
                                expand.setText("收起");
                                replyContent.setHeight(replyContent.getLineHeight() * replyContent.getLineCount());
                            } else if (((TextView)v).getText().toString().equals("收起")) {
                                expand.setText("展开");
                                replyContent.setHeight(replyContent.getLineHeight() * 2);
                            }

                        }
                    });

                }
            }
            else {
                replyContent.setVisibility(View.GONE);
            }
//            vote.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!(comment.isVoted())) {
//                    comment.setVoted(true);
//                    comment.setLikes(comment.getLikes() + 1);
//                } else {
//                    comment.setLikes(comment.getLikes() - 1);
//                    comment.setVoted(false);
//                }
//            }
//        });

            commLinearLayout.addView(commentView);
        }

    }

    private SpannableStringBuilder makeSpStringBuilder(Reply reply) {
        SpannableStringBuilder ssBuilder = new SpannableStringBuilder("//" + reply.getAuthor() + ": " + reply.getContent());
        ssBuilder.setSpan(new StyleSpan(Typeface.BOLD),0,reply.getAuthor().length() + 2, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        ssBuilder.setSpan(new ForegroundColorSpan(Color.BLACK),0,reply.getAuthor().length() + 2,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        ssBuilder.setSpan(new ForegroundColorSpan(CommentActivity.this.getResources().getColor(R.color.text_gray))
                ,reply.getAuthor().length() + 3,ssBuilder.length() - 1
                ,Spanned.SPAN_INCLUSIVE_INCLUSIVE);

        return ssBuilder;
    }

    private void setCommentTextView(Intent intent) {
        long_comments_num = intent.getIntExtra("long_comments", 0);
        short_comments_num = intent.getIntExtra("short_comments", 0);
        total_comments = intent.getIntExtra("comments",0);
        toolbar.setTitle(String.format("%1$d条点评", total_comments));
        longCommentNum.setText(String.format("%1$d条长评", long_comments_num));
        shortCommentNum.setText(String.format("%1$d条短评", short_comments_num));
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_HOME_AS_UP);
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.back));
    }

    private void initViews() {
        setContentView(R.layout.comment_activity);
        toolbar = (Toolbar)findViewById(R.id.comment_toolbar);
        longCommentNum = (TextView)findViewById(R.id.tv_long_comment_num);
        shortCommentNum = (TextView)findViewById(R.id.tv_short_comment_num);
        scrollView = (ScrollView)findViewById(R.id.scroll_view);
        longCommLayout = (LinearLayout)findViewById(R.id.long_comment_list);
        shortCommLayout = (LinearLayout)findViewById(R.id.short_comment_list);


    }

    @Override
    public void showComments(Comments comments,int comment_type) {
        Message msg = Message.obtain();
        if (comment_type == API.LONG_COMMENT_TAG) {
            msg.what = LONG_COMM_MSG;
        } else {
            msg.what = SHORT_COMM_MSG;
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable("comments",comments);
        msg.setData(bundle);
        mCommentHandler.sendMessage(msg);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
