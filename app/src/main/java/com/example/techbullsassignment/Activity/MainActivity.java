package com.example.techbullsassignment.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.techbullsassignment.Adapters.ItemAdapter;
import com.example.techbullsassignment.Models.MovieItem;
import com.example.techbullsassignment.R;
import com.example.techbullsassignment.ViewModels.ItemViewModel;
import com.example.techbullsassignment.ViewModels.SearchViewModel;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    EditText editTextSearch;
    Button buttonSearch;
    ItemViewModel itemViewModel;
    SearchViewModel searchViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting up recyclerview
        recyclerView = findViewById(R.id.recylerview);

        editTextSearch = findViewById(R.id.edit_search);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        //getting our ItemViewModel
        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);

        //creating the Adapter
        final ItemAdapter adapter = new ItemAdapter(this);


        //observing the itemPagedList from view model
        itemViewModel.itemPagedList.observe(this, new Observer<PagedList<MovieItem>>() {
            @Override
            public void onChanged(@Nullable PagedList<MovieItem> items) {

                //in case of any changes
                //submitting the items to adapter
                adapter.submitList(items);
            }
        });

        //setting the adapter
        recyclerView.setAdapter(adapter);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //getting our ItemViewModel
                searchViewModel = ViewModelProviders.of(MainActivity.this).get(SearchViewModel.class);

                //observing the itemPagedList from view model
                searchViewModel.itemPagedList.observe(MainActivity.this, new Observer<PagedList<MovieItem>>() {
                    @Override
                    public void onChanged(@Nullable PagedList<MovieItem> items) {

                        //in case of any changes
                        //submitting the items to adapter
                        adapter.submitList(items);
                    }
                });

            }
        });


    }
}