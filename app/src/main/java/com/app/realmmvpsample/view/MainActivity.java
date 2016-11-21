package com.app.realmmvpsample.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.app.realmmvpsample.model.Post;
import com.app.realmmvpsample.realmmvpsample.R;
import com.app.realmmvpsample.services.ForumService;
import com.app.realmmvpsample.services.RealmService;

import java.util.List;

import io.realm.Realm;


public class MainActivity extends AppCompatActivity implements TodoTasksContract.View {

    private TodoTasksContract.Presenter mPresenter;
    ForumService mForumService;
    RealmService mRealmService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mForumService = new ForumService();

        // Get a Realm instance for this thread
        Realm realm = Realm.getDefaultInstance();
        mRealmService = new RealmService(realm);
        mPresenter = new MainPresenter(this, mForumService,mRealmService);
        mPresenter.displayPostsFromDB();
        mPresenter.loadPosts();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(TodoTasksContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void displayPosts(List<Post> posts) {
        mPresenter.displayPostsFromDB();
    }
}
