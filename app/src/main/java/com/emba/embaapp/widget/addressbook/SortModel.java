package com.emba.embaapp.widget.addressbook;

/**
 * Created by win7 on 16/7/13.
 */
public class SortModel {

    private String useId;
    private String userName;
    private String userIcon;
    private String department;
    private String name;   //显示的数据
    private String sortLetters;  //显示数据拼音的首字母

    public String getUseId() {
        return useId;
    }

    public void setUseId(String useId) {
        this.useId = useId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSortLetters() {
        return sortLetters;
    }
    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }

    @Override
    public String toString() {
        return "SortModel{" +
                "useId='" + useId + '\'' +
                ", userName='" + userName + '\'' +
                ", userIcon='" + userIcon + '\'' +
                ", department='" + department + '\'' +
                ", name='" + name + '\'' +
                ", sortLetters='" + sortLetters + '\'' +
                '}';
    }
}