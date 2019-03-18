package com.webucation;

import static org.junit.Assert.assertTrue;

import com.webucation.impl.FaxuanLogin;
import com.webucation.pojo.Result;
import org.junit.Test;

import java.util.Scanner;

/**
 * 测试1111
 */
public class FaxuanLoginTest {

    @Test
    public void login() {
        FaxuanLogin faxuanLogin = FaxuanLogin.create();

        faxuanLogin.showVerifyImage();//显示验证码图片
        Scanner scanner = new Scanner(System.in);//输入验证码
        Result res = faxuanLogin.login("5306011270065", "zhu669811", scanner.next());
        System.out.println(res);
    }
}
