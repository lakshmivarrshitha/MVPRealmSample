package com.app.realmmvpsample.view;

import android.util.Log;

import com.app.realmmvpsample.model.Post;
import com.app.realmmvpsample.services.ForumService;
import com.app.realmmvpsample.services.RealmService;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Venkatesan on 11/19/2016.
 */


public class MainPresenter implements TodoTasksContract.Presenter {

    public final TodoTasksContract.View  mMainView;
    ForumService mForum;
    RealmService mRealm;

    public MainPresenter(TodoTasksContract.View mainView, ForumService forum, RealmService realm){
        mMainView = mainView;
        mForum = forum;
        mRealm = realm;
        mMainView.setPresenter(this);
    }

    @Override
    public void start() {
        // loadJsonData();
        Log.d("MainPresenter","Start");
    }

    @Override
    public void loadPosts() {
        Log.d("MainPresenter","loadPosts");
        mForum.getApi()
                .getPosts()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Post>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Post> posts) {
                        mRealm.addFromRetrofit(posts);
                        mMainView.displayPosts(posts);
                    }
                });
    }

    @Override
    public void displayPostsFromDB() {
        List<Post> posts = mRealm.getAllPosts();
        Log.d("-----From Realm DB-----",posts.toString());
    }
}
