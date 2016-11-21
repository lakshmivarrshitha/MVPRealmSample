package com.app.realmmvpsample.services;

import android.util.Log;

import com.app.realmmvpsample.model.Post;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by Venkatesan on 11/20/2016.
 */

public class RealmService {

    private final Realm mRealm;

    public RealmService(final Realm realm){
        mRealm = realm;
    }

    public void closeRealm(){
        mRealm.close();
    }

    public RealmResults<Post> getAllPosts(){
        try {
            RealmQuery<Post> query = mRealm.where(Post.class);
            RealmResults<Post> results = query.findAll();
            return results;
        }catch (Exception e){
            Log.d("GetAllPostsException",e.toString());
        }
        return null;
    }

    public void addPostAsync(List<Post> posts){
        
    }

    public void addFromRetrofit(List<Post> posts){
        mRealm.beginTransaction();
        List<Post> realmRepos = mRealm.copyToRealmOrUpdate(posts);
        mRealm.commitTransaction();
    }


    public interface OnTransactionCallback {
        void onRealmSuccess();
        void onRealmError(final Exception e);
    }

}
