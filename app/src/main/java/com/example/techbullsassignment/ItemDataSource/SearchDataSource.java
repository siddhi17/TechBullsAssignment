package com.example.techbullsassignment.ItemDataSource;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.techbullsassignment.Models.MovieItem;
import com.example.techbullsassignment.NetworkCalls.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchDataSource extends PageKeyedDataSource<Integer, MovieItem> {

    public static final int PAGE_SIZE = 5;
    private static final int FIRST_PAGE = 1;
    public static final String SEARCH_QUERY ="";

    //this will be called once to load the initial data
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, MovieItem> callback) {
        RetrofitClient.getInstance()
                .getApi().searchMovies(SEARCH_QUERY,FIRST_PAGE, PAGE_SIZE)
                .enqueue(new Callback<MovieItem>() {
                    @Override
                    public void onResponse(Call<MovieItem> call, Response<MovieItem> response) {
                        if (response.body() != null) {

                            Log.d("List", String.valueOf(response.body().Search.size()));

                            callback.onResult(response.body().Search, null, FIRST_PAGE + 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieItem> call, Throwable t) {

                        Log.d("Failed", t.getMessage());
                    }
                });
    }

    //this will load the previous page
    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, MovieItem> callback) {
        RetrofitClient.getInstance()
                .getApi().searchMovies(SEARCH_QUERY,params.key, PAGE_SIZE)
                .enqueue(new Callback<MovieItem>() {
                    @Override
                    public void onResponse(Call<MovieItem> call, Response<MovieItem> response) {

                        //if the current page is greater than one
                        //we are decrementing the page number
                        //else there is no previous page
                        Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                        if (response.body() != null) {

                            //passing the loaded data
                            //and the previous page key
                            callback.onResult(response.body().Search, adjacentKey);
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieItem> call, Throwable t) {

                        Log.d("Failed", t.getMessage());
                    }
                });
    }

    //this will load the next page
    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, MovieItem> callback) {
        RetrofitClient.getInstance()
                .getApi()
                .searchMovies(SEARCH_QUERY,params.key, PAGE_SIZE)
                .enqueue(new Callback<MovieItem>() {
                    @Override
                    public void onResponse(Call<MovieItem> call, Response<MovieItem> response) {

                        if (response.body() != null) {
                            //if the response has next page
                            //incrementing the next page number
                            Integer key = response.body().has_more ? params.key + 1 : null;

                            //passing the loaded data and next page value
                            callback.onResult(response.body().Search, key);
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieItem> call, Throwable t) {

                        Log.d("Failed", t.getMessage());

                    }
                });
    }
}
