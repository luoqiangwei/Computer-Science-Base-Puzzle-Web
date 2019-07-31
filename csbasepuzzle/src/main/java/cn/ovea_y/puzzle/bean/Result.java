package cn.ovea_y.puzzle.bean;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Result {
    private String id;
    private String user_id;
    private Map<String, ResultItem> answers;
    private Date resultDate;
    private Double grade;

    @Override
    public String toString() {
        return "Result{" +
                "id='" + id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", answers=" + answers +
                ", resultDate=" + resultDate +
                ", grade=" + grade +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Map<String, ResultItem> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<String, ResultItem> answers) {
        this.answers = answers;
    }

    public Date getResultDate() {
        return resultDate;
    }

    public void setResultDate(Date resultDate) {
        this.resultDate = resultDate;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public Result() {
    }
}
