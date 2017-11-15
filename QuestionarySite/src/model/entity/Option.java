package model.entity;

import java.util.List;

public class Option {

    private int id;

    private String answer;

    private String imgPath;

    private List<String> descriptionImgPaths;

    private List<String> links;

    private String description;

    private Interview interview;

    private int interviewId;

    private int voteCount;


    public Option() {

    }

    public Option(int id, String answer, String imgPath, String description, int interviewId, int voteCount) {
        this.id = id;
        this.answer = answer;
        this.imgPath = imgPath;
        this.description = description;
        this.interviewId = interviewId;
        this.voteCount = voteCount;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public int getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(int interviewId) {
        this.interviewId = interviewId;
    }

    public int getId() {
        return id;
    }

    public String getAnswer() {

        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<String> getDescriptionImgPaths() {
        return descriptionImgPaths;
    }

    public void setDescriptionImgPaths(List<String> descriptionImgPaths) {
        this.descriptionImgPaths = descriptionImgPaths;
    }

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Interview getInterview() {
        return interview;
    }

    public void setInterview(Interview interview) {
        this.interview = interview;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }
}