package com.example.techbullsassignment.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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
import com.example.techbullsassignment.Factorys.SearchDataSourceFactory;
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
    public static MovieItem movieItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting up recyclerview
        recyclerView = findViewById(R.id.recylerview);

        editTextSearch = findViewById(R.id.edit_search);

        buttonSearch = findViewById(R.id.search_button);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        //getting our 
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

                movieItem = new MovieItem();
                movieItem.search_query = editTextSearch.getText().toString();

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
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

    }
}