package com.webucation.impl.faxuan;

import com.webucation.impl.OkHttpClientFactory;
import com.webucation.pojo.faxuan.FaxuanUserInfo;
import com.webucation.pojo.Result;
import okhttp3.*;

import java.io.IOException;

public class Faxuanlearn {
    private OkHttpClient client = OkHttpClientFactory.getInstance();


    private FaxuanUserInfo userInfo = null; //用来存放用户信息

    public Faxuanlearn(FaxuanUserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Result getScore() {
        getCourseScore();
        getCourseScore();
        getCourseScore();
        getExerciseScore();
        getExerciseScore();
        getExerciseScore();
        getLoginScore();
        return new Result(true, "学习完毕！");
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
        } finally {
            response.close();
        }
//        System.out.println("getLoginScore:"+responseString);
        return null;
    }

    private Result getExerciseScore() {
        String exercisescore_api = "http://mobile.faxuan.net/pss/service/postPoint?operateType=epoint&userAccount=" + userInfo.getUserAccount() + "&domainCode=" + userInfo.getDomainCode() + "&timestamp=Wed%20Jun%2027%202018%2023:17:20%20GMT+0800%20(%E4%B8%AD%E5%9B%BD%E6%A0%87%E5%87%86%E6%97%B6%E9%97%B4)&exerScore=70&ssid=" + userInfo.getSid() + "&expointValue=20";
        String cookie = "loginUser%3D%7B%22sid%22%3A%22" + userInfo.getSid() + "%22%2C%22id%22%3A%229790434%22%7D";
        Headers headers = new Headers.Builder().add("cookie", cookie).build();
        Request request = new Request.Builder().headers(headers).url(exercisescore_api).get().build();
        Response response = null;
        String responseString = null;
        try {
            response = client.newCall(request).execute();
            responseString = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            response.close();
        }
//        System.out.println("getExerciseScore:"+responseString);
        commitExercise();
        return null;
    }

    private Result commitExercise() {
        String commit_api = "http://xf.faxuan.net/sss/service/coursewareService!commitExercises.do";
        String cookie = "loginUser%3D%7B%22sid%22%3A%22" + userInfo.getSid() + "%22%2C%22id%22%3A%229790434%22%7D";
        Headers headers = new Headers.Builder().add("cookie", cookie).build();
        String postdata = "domainCode=" + userInfo.getDomainCode() + "&userAccount=" + userInfo.getUserAccount() + "&paperId=2108&series=38&myExamAnswer=%5B%7B%22questionId%22%3A%22312187%22%2C%22answerNo%22%3A%22D%22%7D%2C%7B%22questionId%22%3A%22312189%22%2C%22answerNo%22%3A%22D%22%7D%2C%7B%22questionId%22%3A%22312183%22%2C%22answerNo%22%3A%22B%22%7D%2C%7B%22questionId%22%3A%22312186%22%2C%22answerNo%22%3A%22B%22%7D%2C%7B%22questionId%22%3A%22312190%22%2C%22answerNo%22%3A%22A%22%7D%2C%7B%22questionId%22%3A%22312184%22%2C%22answerNo%22%3A%22C%22%7D%2C%7B%22questionId%22%3A%22312185%22%2C%22answerNo%22%3A%22C%22%7D%2C%7B%22questionId%22%3A%22312191%22%2C%22answerNo%22%3A%22C%22%7D%2C%7B%22questionId%22%3A%22312182%22%2C%22answerNo%22%3A%22A%22%7D%2C%7B%22questionId%22%3A%22312188%22%2C%22answerNo%22%3A%22B%22%7D%2C%7B%22questionId%22%3A%22312197%22%2C%22answerNo%22%3A%22%22%7D%2C%7B%22questionId%22%3A%22312199%22%2C%22answerNo%22%3A%22%22%7D%2C%7B%22questionId%22%3A%22312193%22%2C%22answerNo%22%3A%22%22%7D%2C%7B%22questionId%22%3A%22312196%22%2C%22answerNo%22%3A%22%22%7D%2C%7B%22questionId%22%3A%22312200%22%2C%22answerNo%22%3A%22%22%7D%2C%7B%22questionId%22%3A%22312194%22%2C%22answerNo%22%3A%22%22%7D%2C%7B%22questionId%22%3A%22312195%22%2C%22answerNo%22%3A%22%22%7D%2C%7B%22questionId%22%3A%22312201%22%2C%22answerNo%22%3A%22%22%7D%2C%7B%22questionId%22%3A%22312192%22%2C%22answerNo%22%3A%22%22%7D%2C%7B%22questionId%22%3A%22312198%22%2C%22answerNo%22%3A%22%22%7D%2C%7B%22questionId%22%3A%22312207%22%2C%22answerNo%22%3A%22B%22%7D%2C%7B%22questionId%22%3A%22312209%22%2C%22answerNo%22%3A%22A%22%7D%2C%7B%22questionId%22%3A%22312203%22%2C%22answerNo%22%3A%22B%22%7D%2C%7B%22questionId%22%3A%22312206%22%2C%22answerNo%22%3A%22B%22%7D%2C%7B%22questionId%22%3A%22312210%22%2C%22answerNo%22%3A%22B%22%7D%2C%7B%22questionId%22%3A%22312204%22%2C%22answerNo%22%3A%22B%22%7D%2C%7B%22questionId%22%3A%22312205%22%2C%22answerNo%22%3A%22A%22%7D%2C%7B%22questionId%22%3A%22312211%22%2C%22answerNo%22%3A%22A%22%7D%2C%7B%22questionId%22%3A%22312202%22%2C%22answerNo%22%3A%22B%22%7D%2C%7B%22questionId%22%3A%22312208%22%2C%22answerNo%22%3A%22B%22%7D%5D&ssid=" + userInfo.getSid() + "&type=1&timestamp=Thu+Jun+28+2018+00%3A31%3A23+GMT%2B0800+(%E4%B8%AD%E5%9B%BD%E6%A0%87%E5%87%86%E6%97%B6%E9%97%B4)&validate=HNGSMd8Jg7HYjt-mSE7.Xd.2mPap1bZUn1tRbEkYhFoybLQULD0_Ovu-gKlZ8Lj-ENf7TMs-G4.mFjl1R0qq7BfKvYZqSqaaTxqJMLhosD-d9ZIVPZbPlLTqcxskqHoBwJS1ItoyF5XkB7SkgDWuuFFGGBjnFMhZCufwzJoDqGp6qPQdRfP-pmOcjkQxn66kD6JQBbalxK.uDU5aYFah76C8Wd2sZwi2aZMlmaEaSJX9fpSWTC_.xxY87VUNIcJw4Nw-fBeLCErKbyC6eLjvHefec.NWULM.qrA5rm_hwjQQo74PYjoTfZkWTory6X-AjKoCXyGRkravA.6xtwrx4kOwUXD6Ab6fc-suj6L8e2IO2cFzcf8nB.p19AMeqrkHV-cJSm-DxaZ.bnEXzKC99D7mmUYKTZCYh.H2tsbUohiXBDB.Dou.-da.5HWOK.rE0aWZVFFc4Ec.VFz1tfnxrrvowt0oFrfeQBNX8EhGAWC5brofAvRejONlKLq3";
        MediaType mediaType = MediaType.get("application/x-www-form-urlencoded");
        RequestBody requestBody = RequestBody.create(mediaType, postdata);
        Request request = new Request.Builder().headers(headers).url(commit_api).post(requestBody).build();
        Response response = null;
        String responseString = null;
        try {
            response = client.newCall(request).execute();
            responseString = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            response.close();
        }
//        System.out.println("commitExercise:"+responseString);
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
        } finally {
            response.close();
        }
//        System.out.println("getCourseScore"+responseString);
        commitCourse();

        return new Result(true, responseString);
    }

    private Result commitCourse() {
        String commitcourse_api = "http://xf.faxuan.net/sss/service/coursewareService!commitStudy.do";
        MediaType mediaType = MediaType.get("application/x-www-form-urlencoded");
        String postdata = "domainCode=%s&userAccount=%s&stime=31&ssid=%s&type=1&timestamp=%s";
        postdata = String.format(postdata, userInfo.getDomainCode(), userInfo.getUserAccount(), userInfo.getSid(), System.currentTimeMillis());
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
        } finally {
            response.close();
        }
//        System.out.println("commitCourse:"+responseString);
        return null;
    }

}
