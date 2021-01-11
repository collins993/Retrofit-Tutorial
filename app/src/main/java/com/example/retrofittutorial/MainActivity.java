package com.example.retrofittutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text_view);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<ArrayList<Post>> arrayListCall = jsonPlaceHolderApi.getPost();
        arrayListCall.enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                if (!response.isSuccessful()) {
                    textView.setText("Code: " + response.code());
                    return;
                }
                else {
                    ArrayList<Post> posts = response.body();

                    for (Post post : posts) {
                        String content = "";
                        content += "ID: " + post.getId() + "\n";
                        content += "UserId: " + post.getUserId() + "\n";
                        content += "Title: " + post.getTitle() + "\n";
                        content += "Text: " + post.getText() + "\n\n";

                        textView.append(content);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });

    }
}