package com.emba.embaapp;

/**
 * Created by Administrator on 2016-12-02
 *
 * @desc app常量类
 */

public interface AppConstant {

    String SESSIONID_KEY = "sessionId";
    String MY_ACCOUNT = "my_account";
    String MY_PWD = "my_pwd";
    String IS_SAVE_ACCOUNT = "IS_SAVE_ACCOUNT";
    String IS_SAVE_PWD = "IS_SAVE_PWD";
    // 是否登录
    String IS_LOGIN = "is_login";

    String BASE_URL = "http://beiyou.it371.cn/emba2";

    // 登录地址
    String loginUrl = BASE_URL+"/?";

    // 服务需求
    String server_require = BASE_URL+"/serviceNeeds;jsessionid=";
    // 消息列表
    String message_list = BASE_URL+"/infoList;jsessionid=";
    // 个人中心
    String mine_center = BASE_URL+"/mycenter;jsessionid=";

    // emba登录接口
    String URL_LOGIN = BASE_URL + "/user/isValid";

}
