package com.example.techbullsassignment.Factorys;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.example.techbullsassignment.ItemDataSource.ItemDataSource;
import com.example.techbullsassignment.ItemDataSource.SearchDataSource;
import com.example.techbullsassignment.Models.MovieItem;

public class SearchDataSourceFactory extends DataSource.Factory {

        //creating the mutable live data
        private MutableLiveData<PageKeyedDataSource<Integer, MovieItem>> itemLiveDataSource = new MutableLiveData<>();

        private String movie_query;

        public SearchDataSourceFactory(String Query){

            movie_query  = Query;
        }

        @Override
        public DataSource<Integer, MovieItem> create() {
            //getting our data source object
            SearchDataSource itemDataSource = new SearchDataSource(movie_query);

            //posting the datasource to get the values
            itemLiveDataSource.postValue(itemDataSource);

            //returning the datasource
            return itemDataSource;
        }


        //getter for itemlivedatasource
        public MutableLiveData<PageKeyedDataSource<Integer, MovieItem>> getItemLiveDataSource() {
            return itemLiveDataSource;
        }
}
