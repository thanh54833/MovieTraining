package com.example.thanh.movietraining.model;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;

import com.example.library.Converter;
import com.example.library.Files;
import com.example.thanh.movietraining.BuildConfig;
import com.example.thanh.movietraining.presenter.ILoadListener;
import com.example.thanh.movietraining.retrofix.model.Logins;
import com.example.thanh.movietraining.retrofix.model.Movies;
import com.example.thanh.movietraining.retrofix.service.APIClient;
import com.example.thanh.movietraining.retrofix.service.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.thanh.movietraining.Utils.FILE_DATABASE;

public class UserInterator {
    private ILoadListener loadListener;
    private String page;
    private String per_page;
    private static final String Tag="movietraining.model";

    public UserInterator(String page,String per_page,ILoadListener loadListener) {
        this.loadListener = loadListener;
        this.page=page;
        this.per_page=per_page;
    }

    public void createListData(){
        ArrayList<Movie> list = new ArrayList<>();
        for (int i = 0 ; i <10; i++){
            list.add(new Movie("Movie name ", "Name", "100","http://training-movie.bsp.vn:82/upload/movie/sdasdasda.jpg","Ba tên trộm liều lĩnh đột nhập vào nhà một người đàn ông giàu có bị mù. Lũ trộm cho rằng bản thân sẽ vớ bở, thế nhưng chúng đã sai. Trong bóng tối, kẻ mù làm vua. Người đàn ông tưởng chừng yếu đuối nay lại trở thành ác quỷ đưa bọn chúng xuống địa ngục.",false));
        }
        loadListener.loadSuccess(list);
    }

    public void LoadListView(){

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        final Call<Movies> call = apiInterface.onListView("dCuW7UQMbdvpcBDfzolAOSGFIcAec11a",page,per_page);

        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                if(response.isSuccessful()){

                    Movies movies=response.body();
                    ArrayList<Movie> list = new ArrayList<>();

                    for(Movies.Data data:movies.getData()){



                        String title=data.getTitle();
                        String movie = null;
                        String name=null;
                        String decription=data.getDescription();
                        String id=data.getId();
                        boolean Like;

                        ArrayList<Integer> integers=new ArrayList<>();
                        Converter converter;

                        byte[] buffer = Files.readFile(FILE_DATABASE);
                        if(buffer!=null){

                            converter=new Converter(buffer);
                            do {
                                integers.add(converter.byteToInterger());
                            } while (converter.getBuffer() != 0);

                            if(integers.indexOf(Integer.valueOf(data.getId()))!=-1){
                                Like=true;
                            }
                            else {
                                Like=false;
                            }

                            Log.d("thanh.result.id","result : id "+data.getId()+" -- index :"+data.getId()+"id :"+integers);
                        }
                        else {
                            if(BuildConfig.DEBUG){
                               Log.d("thanh.result","byte by null");
                            }
                            Like=false;
                        }


                        if(decription.length()>250){
                            decription=decription.substring(0,250)+"...";
                        }

                        if(title.indexOf("/")>0){
                            movie=title.substring(0,title.indexOf("/"));
                            name=title.substring(title.indexOf("/")+1,title.length());
                        }
                        else {
                            movie=title;
                            name=title;
                        }
                        Log.d("thanh.result","result : \n movie :"+movie+"\n name :"+name);

                        Movie movieItem=new Movie(movie.trim(),name.trim(),"Lượt xem : "+data.getViews(),data.getImage(),decription.trim(),Like,id);
                        list.add(movieItem);
                    }
                    if(BuildConfig.DEBUG){
                        Log.d(Tag,"result : "+movies.getData().length);
                    }

                    loadListener.loadSuccess(list);

                }
            }


            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                if (BuildConfig.DEBUG) {
                    Log.d(Tag, "exception :" + t.getMessage());
                }
                call.cancel();
            }


        });


    }





}
