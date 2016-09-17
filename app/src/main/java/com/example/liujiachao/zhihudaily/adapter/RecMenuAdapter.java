package com.example.liujiachao.zhihudaily.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liujiachao.zhihudaily.interfaces.MenuCallback;
import com.example.liujiachao.zhihudaily.entity.MyTheme;
import com.example.liujiachao.zhihudaily.R;
import com.example.liujiachao.zhihudaily.widgets.MenuTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by evilchaos on 16/8/28.
 */
public class RecMenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private List<MyTheme> myThemeList =  new ArrayList<>();
    final static int TYPE_HEADER = 0;
    final static int TYPE_ITEM = 1;
    final static int SELECTED_GRAY = Color.parseColor("#f0f0f0");
    final static int UNSELECTED_WHITE = Color.WHITE;
    private OnItemClickListener mOnItemClickListener;
    private MenuCallback mCallback;


    public RecMenuAdapter(MenuCallback mCallback) {
        this.mCallback = mCallback;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_HEADER) {
            View view = inflater.inflate(R.layout.menu_header_layout,parent,false);
            return new HeaderViewHolder(view);
        } else  {
            View view = inflater.inflate(R.layout.menu_theme_item,parent,false);
            return new MenuItemViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof HeaderViewHolder) {
            final HeaderViewHolder headerViewHolder = (HeaderViewHolder)holder;
            headerViewHolder.headerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = headerViewHolder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(headerViewHolder.headerView,pos);
                }
            });

        } else {
            final MenuItemViewHolder menuItemViewHolder = (MenuItemViewHolder)holder;
            final MyTheme myTheme = myThemeList.get(position - 1);

            if (myTheme.isSelected()) {
                menuItemViewHolder.menuTextView.setBackgroundColor(SELECTED_GRAY);
            } else {
                menuItemViewHolder.menuTextView.setBackgroundColor(UNSELECTED_WHITE);
            }
            menuItemViewHolder.menuTextView.setSubscribed(myTheme.isSubscribed());
            menuItemViewHolder.menuTextView.setText(myTheme.getTheme().getName());


            menuItemViewHolder.menuTextView.setOnMenuHandleListener(new MenuTextView.OnMenuHandleListener() {
                @Override
                public void onRedrawMenu() {
                    mCallback.onRedrawMenu(position);

                }

                @Override
                public void onHandleEvent() {
                    mCallback.onHandleEvent(position);
                }
            });
        }

    }


    public void updateData(List<MyTheme> data) {

       //更改数据源，不能直接赋值
       //this.myThemeList = myThemeList;
        myThemeList.clear();
        myThemeList.addAll(data);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return myThemeList == null ? 0:myThemeList.size() + 1 ;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0 ) {
            return TYPE_HEADER;
        } else {
            return TYPE_ITEM;
        }
    }


    public class HeaderViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView loginView;
        public TextView collectView;
        public TextView downloadView;
        public TextView homeView;
        public View headerView;

        public HeaderViewHolder(View view) {
            super(view);
            imageView = (ImageView)view.findViewById(R.id.iv_avatar);
            loginView = (TextView)view.findViewById(R.id.tv_login);
            collectView = (TextView)view.findViewById(R.id.tv_collect);
            downloadView = (TextView)view.findViewById(R.id.tv_download);
            homeView = (TextView)view.findViewById(R.id.tv_home);
            headerView = view.findViewById(R.id.header_menu_view);
        }
    }

    public class MenuItemViewHolder extends RecyclerView.ViewHolder {
        public MenuTextView menuTextView;

        public MenuItemViewHolder(View view) {
            super(view);
            menuTextView = (MenuTextView) itemView.findViewById(R.id.menu_theme_item);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view ,int position);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }




}
