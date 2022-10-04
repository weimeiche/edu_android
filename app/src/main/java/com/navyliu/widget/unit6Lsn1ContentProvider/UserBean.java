package com.navyliu.widget.unit6Lsn1ContentProvider;

class UserBean {
    private String name, password, id;

    UserBean() {
    }

    UserBean(String name, String password, String id) {
        this.name = name;
        this.password = password;
        this.id = id;
    }

    public String getUser() {
        return "ID：" + this.id + "，名称：" + this.name + ",密码：" + password;
    }

    public String getName() {
        return name;
    }

    public UserBean setName(String name) {
        this.name = name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserBean setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getId() {
        return id;
    }

    public UserBean setId(String id) {
        this.id = id;
        return this;
    }
}
