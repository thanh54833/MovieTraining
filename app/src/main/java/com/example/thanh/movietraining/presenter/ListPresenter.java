package com.example.thanh.movietraining.presenter;

import com.example.library.Converter;
import com.example.library.Files;
import com.example.thanh.movietraining.Utils;
import com.example.thanh.movietraining.model.MovieModel;
import com.example.thanh.movietraining.object.Movie;
import com.example.thanh.movietraining.retrofix.service.APIClient;
import com.example.thanh.movietraining.retrofix.service.APIInterface;
import com.example.thanh.movietraining.view.IListMovieView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.thanh.movietraining.Utils.FILE_DATABASE;

public class ListPresenter implements IListPresenter {

    private IListMovieView listMovie;
    public ListPresenter(IListMovieView listMovie) {
        this.listMovie = listMovie;
    }
    @Override
    public void LoadListView(String page, String per_page) {

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        final Call<MovieModel> call = apiInterface.onListView(Utils.key_token, page, per_page);

        call.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                if (response.isSuccessful()) {

                    MovieModel movies = response.body();
                    ArrayList<Movie> list = new ArrayList<>();
                    for (MovieModel.Data data : movies.getData()) {

                        String title = data.getTitle();
                        String movie = null;
                        String name = null;
                        String decription = data.getDescription();
                        String id = data.getId();

                        String director=data.getDirector();
                        String manufacturer=data.getManufacturer();
                        String duration=data.getDuration();
                        String actor=data.getActor();
                        String link=data.getLink();
                        String genres=data.getCategory();

                        boolean Like;
                        ArrayList<Integer> integers = new ArrayList<>();
                        Converter converter;
                        byte[] buffer = Files.readFile(FILE_DATABASE);
                        if (buffer.length != 0) {
                            converter = new Converter(buffer);
                            do {
                                integers.add(converter.byteToInterger());
                            } while (converter.getBuffer() != 0);

                            if (integers.indexOf(Integer.valueOf(data.getId())) != -1) {
                                Like = true;
                            } else {
                                Like = false;
                            }
                        } else {
                            Like = false;
                        }

                        if (title.indexOf("/") > 0) {
                            movie = title.substring(0, title.indexOf("/"));
                            name = title.substring(title.indexOf("/") + 1, title.length());
                        } else {
                            movie = title;
                            name = title;
                        }

                        Movie movieItem = new Movie(movie.trim(), name.trim(), "Lượt xem : " + data.getViews(), data.getImage(), decription.trim(), Like, id,director,manufacturer,duration,actor,link,genres);
                        list.add(movieItem);
                    }
                    listMovie.displayMain(list);
                    listMovie.hideLoading();
                }
            }
            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                call.cancel();
            }
        });

    }

}
