package com.example.techbullsassignment.ViewModels;

import android.text.Editable;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.example.techbullsassignment.Factorys.SearchDataSourceFactory;
import com.example.techbullsassignment.ItemDataSource.ItemDataSource;
import com.example.techbullsassignment.Models.MovieItem;

public class SearchViewModel extends ViewModel {

    //creating livedata for PagedList  and PagedKeyedDataSource
    public LiveData<PagedList<MovieItem>> itemPagedList;
    LiveData<PageKeyedDataSource<Integer, MovieItem>> liveDataSource;
    private String movie_query;

    //constructor
    public SearchViewModel() {
        //getting our data source factory
        SearchDataSourceFactory itemDataSourceFactory = new SearchDataSourceFactory(movie_query);

        //getting the live data source from data source factory
        liveDataSource = itemDataSourceFactory.getItemLiveDataSource();

        //Getting PagedList config
        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(ItemDataSource.PAGE_SIZE).build();

        //Building the paged list
        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory, pagedListConfig))
                .build();
    }

    public void update(String query){
        this.movie_query = query;
    }
/*
    public void afterTextChanged(Editable editable) {
        Log.w("tag", "onTextChanged " + editable);
        movie_query = editable.toString();
    }*/
}
