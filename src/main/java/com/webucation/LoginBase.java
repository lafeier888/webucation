package com.webucation;

import com.webucation.pojo.Result;

public interface LoginBase {
    Result login(String username, String password, String code);
}
