package com.app.realmmvpsample.view;

import com.app.realmmvpsample.BasePresenter;
import com.app.realmmvpsample.BaseView;
import com.app.realmmvpsample.model.Post;

import java.util.List;

/**
 * Created by Venkatesan on 11/19/2016.
 */

public interface TodoTasksContract {

    interface View extends BaseView<Presenter>{
        void displayPosts(List<Post> posts);
    }

    interface Presenter extends BasePresenter{
        void loadPosts();
        void displayPostsFromDB();
    }

}
