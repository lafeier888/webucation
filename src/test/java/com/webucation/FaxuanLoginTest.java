package com.webucation;

import static org.junit.Assert.assertTrue;

import com.alibaba.fastjson.JSON;
import com.webucation.impl.faxuan.FaxuanLogin;
import com.webucation.impl.faxuan.Faxuanlearn;
import com.webucation.pojo.faxuan.FaxuanResult;
import com.webucation.pojo.faxuan.FaxuanUserInfo;
import com.webucation.pojo.Result;

import java.util.Scanner;

/**
 * 测试
 */
public class FaxuanLoginTest {


    public static FaxuanUserInfo login() {
        FaxuanLogin faxuanLogin = FaxuanLogin.create();

        faxuanLogin.showVerifyImage();//显示验证码图片
        Scanner scanner = new Scanner(System.in);//输入验证码
        Result res = faxuanLogin.login("5306011270065", "zhu669811", scanner.next());

        FaxuanResult faxuanResult = JSON.parseObject(res.getData().toString(),FaxuanResult.class);
        FaxuanUserInfo faxuanUserInfo = JSON.parseObject(faxuanResult.getData(), FaxuanUserInfo.class);
        return faxuanUserInfo;
    }

    public static void main(String[] args) {
        FaxuanUserInfo faxuanUserInfo = login();
        Faxuanlearn faxuanlearn = new Faxuanlearn(faxuanUserInfo);
        faxuanlearn.getScore();

//        FaxuanUserInfo faxuanUserInfo = JSON.parseObject("{'todayapoint':10}", FaxuanUserInfo.class);
//        System.out.println(faxuanUserInfo);
    }

}
