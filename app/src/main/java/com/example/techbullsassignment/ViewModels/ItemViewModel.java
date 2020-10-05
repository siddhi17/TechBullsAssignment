package com.example.techbullsassignment.ViewModels;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.example.techbullsassignment.Factorys.ItemDataSourceFactory;
import com.example.techbullsassignment.ItemDataSource.ItemDataSource;
import com.example.techbullsassignment.Models.MovieItem;

public class ItemViewModel extends ViewModel {

    //creating livedata for PagedList  and PagedKeyedDataSource
    public LiveData<PagedList<MovieItem>> itemPagedList;
    LiveData<PageKeyedDataSource<Integer, MovieItem>> liveDataSource;

    //constructor
    public ItemViewModel() {
        //getting our data source factory
        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory();

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
}