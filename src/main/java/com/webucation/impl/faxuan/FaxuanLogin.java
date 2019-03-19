package com.webucation.impl.faxuan;

import com.webucation.LoginBase;
import com.webucation.impl.OkHttpClientFactory;
import com.webucation.pojo.Result;
import com.webucation.util.CookieParse;
import okhttp3.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.HashMap;
import java.util.Map;

public class FaxuanLogin implements LoginBase {


    private static OkHttpClient client = OkHttpClientFactory.getInstance();

    //登录接口地址
    private static String loginUrl = "http://www.faxuan.net/bss/xfservice/userService!doUserLogin.do?";

    //保持本rid不变，可以实现免验证码的效果
    private static String rid = "";

    private static FaxuanLogin faxuanLogin = null;

    static {
        System.out.println("正在使用：法宣在线登录接口！");
        getCookieRid();
    }

    private FaxuanLogin() {

    }

    public static String getRid() {
        return rid;
    }

    /**
     * 获取登录对象（单例）
     *
     * @return
     */
    public static FaxuanLogin create() {
        if (faxuanLogin == null) {
            faxuanLogin = new FaxuanLogin();
            return faxuanLogin;
        } else {
            return faxuanLogin;
        }
    }

    /**
     * 登录接口
     *
     * @param username 账号
     * @param password 密码
     * @param code     验证码
     * @return 成功返回用户信息，错误返回空
     */
    @Override
    public Result login(String username, String password, String code) {
        HashMap<String, String> paramMap = new HashMap();
        paramMap.put("userAccount", username);
        paramMap.put("userPassword", password);
        paramMap.put("code", code);
        paramMap.put("rid", rid);
        paramMap.put("_", String.valueOf(System.currentTimeMillis()));
        String paramString = "";
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            paramString += entry.getKey() + "=" + entry.getValue() + "&";
        }
        paramString = paramString.substring(0, paramString.length() - 1);//构造get参数

        Request request = new Request.Builder()
                .url(loginUrl + paramString)
                .get()
                .build();

        Result result = new Result();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            String resText = response.body().string();
            result.setData(resText);
            result.setSuccess(true);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setData("访问失败！");
            result.setSuccess(false);
            return result;
        } finally {
            response.close();
        }
    }

    /**
     * 获取rid，内部方法，不应该客户端调用
     */
    private static String getCookieRid() {
        String url = "http://www.faxuan.net/bps/site/11.html";
        Request request = new Request.Builder().url(url).get().build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            String setcookie = response.header("Set-Cookie");
            String rid = CookieParse.parse(setcookie).get("rid");
            FaxuanLogin.rid = rid;
            return rid;
        } catch (IOException e) {
            System.out.println("请求rid错误！");
            e.printStackTrace();
            return null;
        }finally {
            response.close();
        }
    }

    /**
     * 获取验证码图片
     *
     * @return
     */
    public InputStream getVerifycodeImage() {

        String imageUrl = "http://xf.faxuan.net/service/gc.html";
        Request request = new Request.Builder().url(imageUrl).header("cookie", "rid=" + rid).get().build();
        Response response = null;
        InputStream inputStream = null;
        try {
            response = client.newCall(request).execute();
            inputStream = response.body().byteStream();
        } catch (IOException e) {
            System.out.println("请求验证码错误！");
            e.printStackTrace();
            return null;
        }finally {
            //response.close();
        }
        return inputStream;
    }

    /**
     * 用于显示验证码（使用swing）
     */
    public void showVerifyImage() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame editorFrame = new JFrame("Image Demo");
                editorFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                BufferedImage image = null;
                try {

                    image = ImageIO.read(getVerifycodeImage());
                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                ImageIcon imageIcon = new ImageIcon(image);
                JLabel jLabel = new JLabel();
                jLabel.setIcon(imageIcon);
                editorFrame.getContentPane().add(jLabel, BorderLayout.CENTER);

                editorFrame.pack();
                editorFrame.setLocationRelativeTo(null);
                editorFrame.setVisible(true);
            }
        });
    }

}
