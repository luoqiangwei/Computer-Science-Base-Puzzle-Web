package cn.ovea_y.puzzle.bean;

import java.util.Date;

public class User {
    private String id;
    private String nickname;
    private String phone;
    private String password;
    private String gender;
    private String avatar;
    private Date createDate;
    private String realName;
    private String idCard;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", avatar='" + avatar + '\'' +
                ", createDate=" + createDate +
                ", readName='" + realName + '\'' +
                ", idCard='" + idCard + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRealName() {
        return realName;
    }

    public void setReadName(String realName) {
        this.realName = realName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public User(String id, String nickname, String phone, String password, String gender, String avatar, Date createDate, String realName, String idCard) {
        this.id = id;
        this.nickname = nickname;
        this.phone = phone;
        this.password = password;
        this.gender = gender;
        this.avatar = avatar;
        this.createDate = createDate;
        this.realName = realName;
        this.idCard = idCard;
    }
}
