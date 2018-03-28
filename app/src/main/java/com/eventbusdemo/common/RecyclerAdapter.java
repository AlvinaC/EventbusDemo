package com.eventbusdemo.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.eventbusdemo.R;
import com.eventbusdemo.activity.MainActivity;
import com.eventbusdemo.models.Events;
import com.eventbusdemo.models.TempModel;
import com.eventbusdemo.util.EventbusApp;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by alvina.rasquinha on 27/03/18.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private Context context;
    private int which;

    public RecyclerAdapter(Context context, int which) {
        this.context = context;
        this.which = which;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        switch (which) {
            case 1:
                TempModel temp = ((MainActivity) context).getList().get(position);
                holder.txtName.setText(temp.getName());
                if (temp.getLike()) holder.imgLikeDislike.setImageResource(R.drawable.like);
                else holder.imgLikeDislike.setImageResource(R.drawable.dislike);
                break;
            case 2:
                TempModel tempFav = ((MainActivity) context).getLikeList().get(position);
                holder.txtName.setText(tempFav.getName());
                break;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        int size = (which == 1) ? ((MainActivity) context).getList().size() : ((MainActivity) context).getLikeList().size();
        return size;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_name)
        TextView txtName;

        @BindView(R.id.img_like_dislike)
        ImageView imgLikeDislike;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            showHideLike();
        }

        private void showHideLike() {
            switch (which) {
                case 1:
                    imgLikeDislike.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    imgLikeDislike.setVisibility(View.GONE);
                    break;
            }

        }

        @OnClick(R.id.img_like_dislike)
        public void change(View view) {
            switch (which) {
                case 1:
                    if (((MainActivity) context).getList().get(getAdapterPosition()).getLike()) {
                        //change current list
                        ((MainActivity) context).getList().get(getAdapterPosition()).setLike(false);
                        //change image
                        imgLikeDislike.setImageResource(R.drawable.dislike);
                        //if in fav list remove
                        String name = ((MainActivity) context).getList().get(getAdapterPosition()).getName();
                        ((MainActivity) context).getLikeList().removeIf(obj -> obj.getName().equals(name));
                    } else {
                        //change cur list
                        ((MainActivity) context).getList().get(getAdapterPosition()).setLike(true);
                        //change image
                        imgLikeDislike.setImageResource(R.drawable.like);
                        //add it to fav list
                        ((MainActivity) context).getLikeList().add(((MainActivity) context).getList()
                                .get(getAdapterPosition()));
                    }

                    //fire an event to reset list
                    ((EventbusApp) ((MainActivity) context).getApplication())
                            .bus()
                            .send(new Events.ListChangeEvent());
                    break;
            }

        }

    }
}
