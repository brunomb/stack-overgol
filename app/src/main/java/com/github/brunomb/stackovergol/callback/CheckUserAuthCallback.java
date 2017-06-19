package com.github.brunomb.stackovergol.callback;

import com.github.brunomb.stackovergol.model.StackOvergolError;
import com.github.brunomb.stackovergol.model.User;

/**
 * Created by brunomb on 6/18/2017.
 */
public interface CheckUserAuthCallback {
    void onSuccess(User authenticatedUser);
    void onFailure(StackOvergolError error);
}
