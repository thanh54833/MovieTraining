package com.example.thanh.movietraining.model;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;

import com.example.thanh.movietraining.BuildConfig;
import com.example.thanh.movietraining.presenter.ILoadListener;
import com.example.thanh.movietraining.retrofix.model.Logins;
import com.example.thanh.movietraining.retrofix.service.APIClient;
import com.example.thanh.movietraining.retrofix.service.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        ArrayList<Movie> list = new ArrayList<>();

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        final Call<Logins> call = apiInterface.onListView("dCuW7UQMbdvpcBDfzolAOSGFIcAec11a",page,per_page);

        call.enqueue(new Callback<Logins>() {
            @Override
            public void onResponse(Call<Logins> call, Response<Logins> response) {
                if(response.isSuccessful()){

                    Log.d(Tag,"result : \n true");

                }
            }

            @Override
            public void onFailure(Call<Logins> call, Throwable t) {
                if (BuildConfig.DEBUG) {
                    Log.d(Tag, "exception :" + t.getMessage());
                }
                call.cancel();
            }
        });



        loadListener.loadSuccess(list);
    }





}
