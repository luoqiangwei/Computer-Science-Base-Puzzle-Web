package cn.oveay.cspuzzle.bean;

public class Commit {
    private String id;
    private String user_id;
    private String puzzle_id;
    private String content;
    private Integer floor;
    private Integer subFloor;
    private String reCall_user_id;

    @Override
    public String toString() {
        return "Commit{" +
                "id='" + id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", puzzle_id='" + puzzle_id + '\'' +
                ", content='" + content + '\'' +
                ", floor=" + floor +
                ", subFloor=" + subFloor +
                ", reCall_user_id='" + reCall_user_id + '\'' +
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

    public String getPuzzle_id() {
        return puzzle_id;
    }

    public void setPuzzle_id(String puzzle_id) {
        this.puzzle_id = puzzle_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getSubFloor() {
        return subFloor;
    }

    public void setSubFloor(Integer subFloor) {
        this.subFloor = subFloor;
    }

    public String getReCall_user_id() {
        return reCall_user_id;
    }

    public void setReCall_user_id(String reCall_user_id) {
        this.reCall_user_id = reCall_user_id;
    }

    public Commit() {
    }
}
