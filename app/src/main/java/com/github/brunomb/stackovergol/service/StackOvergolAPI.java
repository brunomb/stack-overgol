package com.github.brunomb.stackovergol.service;

import com.github.brunomb.stackovergol.model.StackOvergolError;

/**
 * Created by brunomb on 3/24/2017
 */

public class StackOvergolAPI {

    public interface GenericCallback {
        void onSuccess();
        void onFailure(StackOvergolError error);
    }

}
