package com.webucation.impl.faxuan;

import com.webucation.impl.OkHttpClientFactory;
import com.webucation.pojo.faxuan.FaxuanUserInfo;
import com.webucation.pojo.Result;
import okhttp3.*;

import java.io.IOException;

public class Faxuanlearn {
    private  OkHttpClient client = OkHttpClientFactory.getInstance();


    private FaxuanUserInfo userInfo = null; //用来存放用户信息

    public Faxuanlearn(FaxuanUserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Result getScore() {
        Result courseScore = getCourseScore();
        Result exerciseScore = getExerciseScore();
        Result loginScore = getLoginScore();
        return null;
    }

    private Result getLoginScore() {
        String loginscore_api = "http://www.faxuan.net/pss/xfservice/postPoint?operateType=lpoint&userAccount=%s&ssid=%s&exerScore=80&expointValue=0&domainCode=%s";
        loginscore_api = String.format(loginscore_api, userInfo.getUserAccount(), userInfo.getSid(), userInfo.getDomainCode());
        String cookie = "loginUser%3D%7B%22sid%22%3A%22" + FaxuanLogin.getRid() + "%22%2C%22id%22%3A%229790434%22%7D";
        Headers headers = new Headers.Builder().add("cookie", cookie).build();
        Request request = new Request.Builder().headers(headers).url(loginscore_api).get().build();
        Response response = null;
        String responseString = null;
        try {
            response = client.newCall(request).execute();
            responseString = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            response.close();
        }
        System.out.println("getLoginScore:"+responseString);
        return null;
    }

    private Result getExerciseScore() {
        return null;
    }

    private Result getCourseScore() {
        String coursescore_api = "http://mobile.faxuan.net/pss/service/postPoint?operateType=spoint&userAccount=%s&ssid=%s&exerScore=80&expointValue=20&domainCode=%s&stime=40";
        coursescore_api = String.format(coursescore_api, userInfo.getUserAccount(), userInfo.getSid(), userInfo.getDomainCode());
        String cookie = "loginUser%3D%7B%22sid%22%3A%22" + FaxuanLogin.getRid() + "%22%2C%22id%22%3A%229790434%22%7D";

        Headers headers = new Headers.Builder().add("cookie", cookie).build();
        Request request = new Request.Builder().headers(headers).url(coursescore_api).build();
        Response response = null;
        String responseString = null;
        try {
            response = client.newCall(request).execute();
            responseString = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            response.close();
        }
        System.out.println("getCourseScore"+responseString);
        commitCourse();

        return new Result(true, responseString);
    }
    private Result commitCourse(){
        String commitcourse_api = "http://xf.faxuan.net/sss/service/coursewareService!commitStudy.do";
        MediaType mediaType = MediaType.get("application/x-www-form-urlencoded");
        String postdata ="domainCode=%s&userAccount=%s&stime=31&ssid=%s&type=1&timestamp=%s";
        postdata = String.format(postdata,userInfo.getDomainCode(),userInfo.getUserAccount(),userInfo.getSid(),System.currentTimeMillis());
        RequestBody requestBody = RequestBody.create(mediaType, postdata);
        String cookie = "loginUser%3D%7B%22sid%22%3A%22" + FaxuanLogin.getRid() + "%22%2C%22id%22%3A%229790434%22%7D";
        Headers headers = new Headers.Builder().add("cookie", cookie).build();
        Request request = new Request.Builder().headers(headers).url(commitcourse_api).post(requestBody).build();
        Response response = null;
        String responseString = null;
        try {
            response = client.newCall(request).execute();
            responseString = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            response.close();
        }
        System.out.println(responseString);
        return null;
    }

}
