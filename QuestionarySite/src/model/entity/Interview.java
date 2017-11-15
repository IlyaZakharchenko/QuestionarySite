package model.entity;

import java.sql.Date;
import java.util.List;

public class Interview {

    private int id;

    private String question;

    private User owner;
    private int ownerId;
    private Date date;
    private boolean isArchived;
    private int themeId;
    private Theme theme;
    private boolean typeCheck;
    private boolean isAnonymous;
    private boolean canComment;
    private List<Commentary> commentaries;
    private List<Option> options;
    public Interview() {

    }
    public Interview(int id, String question, int ownerId, Date date, boolean isArchived, int themeId, boolean typeCheck, boolean isAnonymous, boolean canComment) {
        this.id = id;
        this.question = question;
        this.ownerId = ownerId;
        this.date = date;
        this.isArchived = isArchived;
        this.themeId = themeId;
        this.typeCheck = typeCheck;
        this.isAnonymous = isAnonymous;
        this.canComment = canComment;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    public boolean isTypeCheck() {
        return typeCheck;
    }

    public void setTypeCheck(boolean typeCheck) {
        this.typeCheck = typeCheck;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean anonymous) {
        isAnonymous = anonymous;
    }

    public boolean isCanComment() {
        return canComment;
    }

    public void setCanComment(boolean canComment) {
        this.canComment = canComment;
    }

    public List<Commentary> getCommentaries() {
        return commentaries;
    }

    public void setCommentaries(List<Commentary> commentaries) {
        this.commentaries = commentaries;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }
}