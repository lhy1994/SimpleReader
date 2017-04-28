package com.example.liuhaoyuan.simplereader.adapter.book;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.liuhaoyuan.simplereader.ConstantValues;
import com.example.liuhaoyuan.simplereader.R;
import com.example.liuhaoyuan.simplereader.adapter.BaseListAdapter;
import com.example.liuhaoyuan.simplereader.bean.book.BookItemBean;
import com.example.liuhaoyuan.simplereader.book.BookDetailActivity;
import com.example.liuhaoyuan.simplereader.util.DataUtils;
import com.example.liuhaoyuan.simplereader.util.ViewUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuhaoyuan on 2017/4/26.
 */

public class BookListAdapter extends BaseListAdapter<List<BookItemBean>, BookListAdapter.BookListHolder> {
    private Context mContext;

    public BookListAdapter(Context context, List<BookItemBean> data) {
        this.mContext = context;
        mData = data;
    }

    @Override
    public BookListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_list, parent, false);
        return new BookListHolder(view);
    }

    @Override
    public void onBindViewHolder(BookListHolder holder, int position) {
        final BookItemBean bean = mData.get(position);
        String imageUrl = DataUtils.getImageUrl(bean.images);
        Glide.with(mContext).load(imageUrl).into(holder.mPosterIv);
        ViewUtils.setTextViewText(holder.mTitleTv, bean.titleX);
        StringBuilder builder=new StringBuilder();
        for (String s : bean.author) {
            builder.append(s).append(" ");
        }
        ViewUtils.setTextViewText(holder.mAuthorTv, builder.toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, BookDetailActivity.class);
                intent.putExtra(ConstantValues.DOUBAN_BOOK_ID,bean.id);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class BookListHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_poster)
        ImageView mPosterIv;
        @BindView(R.id.tv_title)
        TextView mTitleTv;
        @BindView(R.id.tv_author)
        TextView mAuthorTv;

        public BookListHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
