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


    public static FaxuanResult login() {
        FaxuanLogin faxuanLogin = FaxuanLogin.create();

        faxuanLogin.showVerifyImage();//显示验证码图片
        Scanner scanner = new Scanner(System.in);//输入验证码
        Result res = faxuanLogin.login("5206100080783", "zxl123456", scanner.next());

        FaxuanResult faxuanResult = JSON.parseObject(res.getData().toString(), FaxuanResult.class);
//        FaxuanUserInfo faxuanUserInfo = JSON.parseObject(faxuanResult.getData(), FaxuanUserInfo.class);
        return faxuanResult;
    }

    public static void main(String[] args) {
        FaxuanResult faxuanResult = login();//登录
        FaxuanUserInfo faxuanUserInfo = faxuanResult.getData();//获取用户信息
        Faxuanlearn faxuanlearn = new Faxuanlearn(faxuanUserInfo);
        faxuanlearn.getScore();//获取积分
        System.out.println(faxuanResult);
    }

}
