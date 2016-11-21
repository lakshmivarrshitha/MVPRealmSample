package com.app.realmmvpsample.services;

/**
 * Created by Venkatesan on 11/19/2016.
 */




import com.app.realmmvpsample.model.Comment;
import com.app.realmmvpsample.model.Post;

import java.util.List;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;


public class ForumService {

    private static final String FORUM_SERVER_URL = "http://jsonplaceholder.typicode.com";
    private ForumApi mForumApi;

    public ForumService() {


        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Accept", "application/json");
            }
        };

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(FORUM_SERVER_URL)
                .setRequestInterceptor(requestInterceptor)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        mForumApi = restAdapter.create(ForumApi.class);
    }

    public ForumApi getApi() {

        return mForumApi;
    }

    public interface ForumApi {

        @GET("/posts")
        public Observable<List<Post>>
        getPosts();

        @GET("/posts/{id}")
        public Observable<Post>
        getPost(@Path("id") int postId);

        @GET("/comments")
        public Observable<List<Comment>>
        getComments(@Query("postId") int postId);

        @POST("/posts")
        public Observable<Post>
        postPost(Post post);
    }
}