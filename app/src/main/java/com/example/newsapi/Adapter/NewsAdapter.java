package com.example.newsapi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newsapi.Model.NewsModel;
import com.example.newsapi.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> /*implements Filterable */{

    private ArrayList<NewsModel> news = new ArrayList<>();

    //ArrayList<NewsModel> backup;
    private Context context;

    public NewsAdapter(ArrayList<NewsModel> newsModels, Context context){
        news = newsModels;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from( context ).inflate( R.layout.view_layout, parent, false );

        return new NewsViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsAdapter.NewsViewHolder holder, final int position) {

        holder.title.setText(news.get(position).getTitle());
        holder.content.setText(news.get(position).getContent());
        holder.author.setText(news.get(position).getAuthor());
        holder.published_date.setText(news.get(position).getPublishedAt());
        String url=news.get(position).getUrlToImage();
        Picasso.get().load(url).placeholder(R.drawable.baseline_image_24).into(holder.imageView);


    }
    public void updateSearchedList() {



    }

    @Override
    public int getItemCount() {

        return news.size();
    }

  //  @Override
//    public Filter getFilter() {
//        return filter;
//    }

//    Filter filter = new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence keyword) {
//            ArrayList<NewsModel> filterData=new ArrayList<>();
//
//            if (keyword.toString().isEmpty()){
//               // filterData.add(backup);
//            }else{
//                for(NewsModel obj: backup){
//                    if (obj.getContent().toString().toLowerCase().contains(keyword.toString().toLowerCase()))
//                        filterData.add(obj);
//                }
//            }
//            FilterResults  results=new FilterResults();
//            results.values=filterData;
//            return results;
//        }
//
//        @Override //Maoin UI Thread
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//
//            news.clear();
//            news.addAll((ArrayList<NewsModel>)(results.values));
//            notifyDataSetChanged();
//
//        }
//    };

    public class NewsViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView imageView;
        TextView content;
        TextView published_date;
        TextView author;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.news_title_txt);
            content = itemView.findViewById(R.id.news_desc_txt);
            imageView=itemView.findViewById(R.id.image_view);
            published_date=itemView.findViewById(R.id.publish_date_txt);
            author=itemView.findViewById(R.id.aurthor_name_txt);

        }
    }
}
