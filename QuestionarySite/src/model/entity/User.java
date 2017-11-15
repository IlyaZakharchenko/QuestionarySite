package model.entity;

import java.util.List;
import java.util.Map;

public class User {

    private int id;

    private String login;

    private String password;

    private String username;

    private int age;

    private String aboutYourself;

    private String imgPath;

    private List<Commentary> commentaries;

    private List<Map<Interview, Option>> answers;

    public User() {
    }

    public User(int id, String login, String password, String username, int age, String aboutYourself, String imgPath) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.username = username;
        this.age = age;
        this.aboutYourself = aboutYourself;
        this.imgPath = imgPath;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAboutYourself() {
        return aboutYourself;
    }

    public void setAboutYourself(String aboutYourself) {
        this.aboutYourself = aboutYourself;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public List<Commentary> getCommentaries() {
        return commentaries;
    }

    public void setCommentaries(List<Commentary> commentaries) {
        this.commentaries = commentaries;
    }

    public List<Map<Interview, Option>> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Map<Interview, Option>> answers) {
        this.answers = answers;
    }

}