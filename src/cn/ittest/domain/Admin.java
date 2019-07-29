package cn.ittest.domain;

public class Admin {
    private String username;
    private String password;
    private String checkC;

    public void setCheckC(String checkC) {
        this.checkC = checkC;
    }

    public String getCheckC() {
        return checkC;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
