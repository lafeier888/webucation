package com.webucation.impl;

import com.webucation.pojo.FaxuanUserInfo;
import com.webucation.pojo.Result;

public class Faxuanlearn {
    private FaxuanUserInfo userInfo = null;

    public Faxuanlearn(FaxuanUserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Result getScore(){
        Result courseScore = getCourseScore();
        Result exerciseScore = getExerciseScore();
        Result loginScore = getLoginScore();
        return null;
    }

    private Result getLoginScore() {
        return null;
    }

    private Result getExerciseScore() {
        return null;
    }

    private Result getCourseScore() {
        return null;
    }

}
