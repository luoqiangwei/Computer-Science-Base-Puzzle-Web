package cn.oveay.cspuzzle.bean;

public class ResultItem {
    private String id;
    private String result_id;
    private String puzzle_id;
    private String option;

    @Override
    public String toString() {
        return "ResultItem{" +
                "id='" + id + '\'' +
                ", result_id='" + result_id + '\'' +
                ", puzzle_id='" + puzzle_id + '\'' +
                ", option='" + option + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResult_id() {
        return result_id;
    }

    public void setResultitem_id(String result_id) {
        this.result_id = result_id;
    }

    public String getPuzzle_id() {
        return puzzle_id;
    }

    public void setPuzzle_id(String puzzle_id) {
        this.puzzle_id = puzzle_id;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}
