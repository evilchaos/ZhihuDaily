package com.example.liujiachao.zhihudaily.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    private  boolean isHomeSelected = true;


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
            headerViewHolder.collectView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(v);
                }
            });

            headerViewHolder.downloadView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(v);
                }
            });

            headerViewHolder.loginLayoutView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(v);
                }
            });

            if (!isHomeSelected) {
                headerViewHolder.homeView.setBackgroundColor(Color.parseColor("#ffffff"));
            } else {
                headerViewHolder.homeView.setBackgroundColor(SELECTED_GRAY);
            }

            headerViewHolder.homeView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(v);
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
        myThemeList.clear();
        myThemeList.addAll(data);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return myThemeList == null ? 1:myThemeList.size() + 1 ;
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
        public TextView collectView;
        public TextView downloadView;
        public TextView homeView;
        public View headerView;
        public LinearLayout loginLayoutView;

        public HeaderViewHolder(View view) {
            super(view);
            collectView = (TextView)view.findViewById(R.id.tv_collect);
            loginLayoutView = (LinearLayout) view.findViewById(R.id.linear_login);
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
        void onItemClick(View view);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setHomeSelected(boolean isHomeSelected) {
        this.isHomeSelected = isHomeSelected;
    }




}
