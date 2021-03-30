package com.constants;

public enum Credentials {

    user("user", "pass", "400", ""),
    ;

    private String login;
    private String password;
    private String mandate;
    private String name;

    Credentials(String login, String password, String mandate, String name) {
        this.login = login;
        this.password = password;
        this.mandate = mandate;
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getMandate() {
        return mandate;
    }

    public String getName() {
        return name;
    }
}
