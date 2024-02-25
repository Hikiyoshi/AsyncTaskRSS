package com.example.bt2loadrss;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class MyAsyncTask extends android.os.AsyncTask<Void,Void,Void> {
    private Context context;
    private String link;
    private List<Item> list;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;

    public MyAsyncTask(Context context, String link, RecyclerView recyclerView) {
        this.context = context;
        this.link = link;
        this.recyclerView = recyclerView;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            //Kết nối url
            URL url = new URL(link);
            URLConnection connection = url.openConnection();
            InputStream inpS = connection.getInputStream();

            //Xử lý đọc rss về
            list = MYSAXParser.xmlParser(inpS);

        }catch (Exception e){
            Log.d("Error",e.getMessage());
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        adapter = new RecyclerViewAdapter(context,list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView.ItemDecoration id = new DividerItemDecoration(context,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(id);
        recyclerView.setAdapter(adapter);
    }
}
