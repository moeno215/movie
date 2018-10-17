package com.example.maulana.movie.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.maulana.movie.R;

import com.example.maulana.movie.UI.adapter.MyAdapter;
import com.example.maulana.movie.UI.model.ResponseMovie;
import com.example.maulana.movie.UI.model.ResultsItem;
import com.example.maulana.movie.UI.rest.ApiInterface;
import com.example.maulana.movie.UI.rest.Constant;
import com.example.maulana.movie.UI.rest.InstanceRetrofit;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recy)
    RecyclerView rvMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        loadData();
    }

    private void loadData() {
//        ApiService apiService = InstanceRetrofit.getInstance();
        ApiInterface apiService = InstanceRetrofit.getClient().create(ApiInterface.class);
        Call<ResponseMovie> call = apiService.getUpComingMovie(Constant.APIKEY, Constant.LANGUAGE);
        call.enqueue(new Callback<ResponseMovie>() {
            @Override
            public void onResponse(Call<ResponseMovie> call, Response<ResponseMovie> response) {

                if (response.isSuccessful()){
                    List<ResultsItem> responseMovie = response.body().getResults();
//               Log.d(" ", "onResponse data: " + responseMovie);
                    MyAdapter adapter = new MyAdapter(responseMovie, MainActivity.this);
                    rvMovie.setAdapter(adapter);
                    rvMovie.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                }
                else{
                    Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }




            }


            @Override
            public void onFailure(Call<ResponseMovie> call, Throwable t) {
                Log.d("", "onFailure: " + t.toString());
            }
        });
    }


}
