package wangcf.bean;

/**
 * @Author: wangcf
 * @Date: 2019/3/30 18:42
 */
public class User {
    private int userid;
    private String usernam;
    private String password;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsernam() {
        return usernam;
    }

    public void setUsernam(String usernam) {
        this.usernam = usernam;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
