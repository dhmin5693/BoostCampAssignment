package com.min.boostcamp.boostcampassignment.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.min.boostcamp.boostcampassignment.R;
import com.min.boostcamp.boostcampassignment.WebViewActivity;
import com.min.boostcamp.boostcampassignment.common.ViewHolder;
import com.min.boostcamp.boostcampassignment.databinding.ItemMovieBinding;
import com.min.boostcamp.boostcampassignment.vo.MovieItem;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder<ItemMovieBinding>> {

    private Context context;
    private List<MovieItem> items;
    private RecyclerView recyclerView;
    private boolean loading;

    public RecyclerViewAdapter(Context context, RecyclerView recyclerView) {

        this.context = context;
        this.recyclerView = recyclerView;
        this.loading = false;
    }

    public void reset() {
        items = null;
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    /**
     * 기존의 items에 목록을 추가
     * @param list 새로 추가될 영화 목록
     */
    public void addItems(List<MovieItem> list) {

        if (list.isEmpty()) {
            Toast.makeText(
                    context,
                    context.getString(R.string.msg_no_result),
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        if (items == null) {
            items = new ArrayList<>();
        }

        items.addAll(list);
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder<ItemMovieBinding> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder<>(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder<ItemMovieBinding> holder, int position) {

        MovieItem item = items.get(position);

        // dataBinding으로 item, adapter 연결
        holder.getBinding().setItem(item);
        holder.getBinding().setAdapter(this);
    }

    @Override
    public int getItemCount() {
        if (items == null)
            return 0;

        return items.size();
    }

    /**
     * 각 아이템을 클릭하면 링크된 페이지로 이동
     */
    public void onClick(View v) {

        int pos = recyclerView.getChildLayoutPosition(v);
        Log.d("loadWebPage", items.get(pos).getTitle() + "(" + items.get(pos).getLink() + ")");

        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("link", items.get(pos).getLink());
        context.startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }
}