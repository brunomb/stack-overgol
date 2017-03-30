package com.github.brunomb.stackovergol.activity.players;

import com.github.brunomb.stackovergol.model.StackOvergolError;
import com.github.brunomb.stackovergol.model.User;
import com.github.brunomb.stackovergol.service.StackOvergolAPI;
import com.github.brunomb.stackovergol.service.StackOvergolService;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by brunomb on 3/29/2017
 */

public class PlayersPresenter implements PlayersMVP.PresenterOps {

    private WeakReference<PlayersMVP.ViewOps> mView;
    private StackOvergolService stackOvergolService;

    @Override
    public void setService(StackOvergolService service) {
        stackOvergolService = service;
    }

    @Override
    public void getUsers() {
        stackOvergolService.getUsers(new StackOvergolAPI.GetUsersCallback() {
            @Override
            public void onSuccess(List<User> usersList) {
                mView.get().showUsers(usersList);
            }

            @Override
            public void onFailure(StackOvergolError error) {

            }
        });
    }

    PlayersPresenter(PlayersMVP.ViewOps view) {
        mView = new WeakReference<>(view);
    }
}
