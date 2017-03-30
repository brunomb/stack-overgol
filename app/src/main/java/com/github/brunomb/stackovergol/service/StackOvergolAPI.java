package com.github.brunomb.stackovergol.service;

import com.github.brunomb.stackovergol.model.StackOvergolError;
import com.github.brunomb.stackovergol.model.User;

import java.util.List;

/**
 * Created by brunomb on 3/24/2017
 */

public class StackOvergolAPI {

    public interface GenericCallback {
        void onSuccess();
        void onFailure(StackOvergolError error);
    }

    public interface GetUsersCallback {
        void onSuccess(List<User> usersList);
        void onFailure(StackOvergolError error);
    }

}
