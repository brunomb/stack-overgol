package com.github.brunomb.stackovergol.activity.main;

/**
 * Created by brunomb on 3/16/2017
 */

interface MainMVP {

    interface ViewOps {

    }

    interface PresenterOps {
        void initFireBase();
        void setView(ViewOps view);
    }



}
