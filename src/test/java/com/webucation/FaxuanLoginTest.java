package com.webucation;

import static org.junit.Assert.assertTrue;

import com.webucation.impl.FaxuanLogin;
import com.webucation.pojo.Result;
import org.junit.Test;

import java.util.Scanner;

/**
 * 测试
 */
public class FaxuanLoginTest {


    public static void login() {
        FaxuanLogin faxuanLogin = FaxuanLogin.create();

        faxuanLogin.showVerifyImage();//显示验证码图片
        Scanner scanner = new Scanner(System.in);//输入验证码
        Result res = faxuanLogin.login("5306011270065", "zhu669811", scanner.next());
        System.out.println(res);
    }

    public static void main(String[] args) {
        login();
    }

}
