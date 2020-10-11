package com.example.techbullsassignment.Models;

import java.util.List;

public class MovieItem {

    public String imdbID;
    public String Title;
    public String Year;
    public String Poster;
    public List<MovieItem> Search;
    public boolean has_more;

    public String getSearch_query() {
        return search_query;
    }

    public String search_query;
}
