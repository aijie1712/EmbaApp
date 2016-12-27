package com.emba.embaapp;

/**
 * Created by Administrator on 2016-12-02
 *
 * @desc app常量类
 */

public interface AppConstant {

    String SESSIONID_KEY = "sessionId";
    // 是否登录
    String IS_LOGIN = "is_login";

    // 登录地址
    String loginUrl = "http://171.8.196.92:8090/emba/?deviceType=ios";

    // 服务需求
    String server_require = "http://171.8.196.92:8090/emba/serviceNeeds;jsessionid=";
    // 消息列表
    String message_list = "http://171.8.196.92:8090/emba/infoList;jsessionid=";
    // 个人中心
    String mine_center = "http://171.8.196.92:8090/emba/mycenter;jsessionid=";
}
