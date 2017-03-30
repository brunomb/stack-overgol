package com.github.brunomb.stackovergol.activity.players;

import com.github.brunomb.stackovergol.model.User;
import com.github.brunomb.stackovergol.service.StackOvergolService;

import java.util.List;

/**
 * Created by brunomb on 3/29/2017
 */

public interface PlayersMVP {

    interface ViewOps {
        void showUsers(List<User> users);
//        boolean doBindToStackOvergolService(ServiceConnection connection);
//        void doUnbindToStackOvergolService(ServiceConnection connection);
//        void stackOvergolServiceConnected();
//        void stackOvergolServiceDisconnected();
    }

    interface PresenterOps {
        void setService(StackOvergolService service);
        void getUsers();
    }
}
