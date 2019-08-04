package cn.oveay.cspuzzle.bean;

public class PuzzleItem {
    private String id;
    private String puzzle_id;
    private Character option;
    private String content;
    private Boolean isAnswer;


    public PuzzleItem() {
    }

    @Override
    public String toString() {
        return "PuzzleItem{" +
                "id='" + id + '\'' +
                ", puzzle_id='" + puzzle_id + '\'' +
                ", option=" + option +
                ", content='" + content + '\'' +
                ", isAnswer=" + isAnswer +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPuzzle_id() {
        return puzzle_id;
    }

    public void setPuzzle_id(String puzzle_id) {
        this.puzzle_id = puzzle_id;
    }

    public Character getOption() {
        return option;
    }

    public void setOption(Character option) {
        this.option = option;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getAnswer() {
        return isAnswer;
    }

    public void setAnswer(Boolean answer) {
        isAnswer = answer;
    }

    public PuzzleItem(String id, String puzzle_id, Character option, String content, Boolean isAnswer) {
        this.id = id;
        this.puzzle_id = puzzle_id;
        this.option = option;
        this.content = content;
        this.isAnswer = isAnswer;
    }
}
