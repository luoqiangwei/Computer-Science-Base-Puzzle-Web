package cn.ovea_y.puzzle.bean;

import java.util.List;
import java.util.Map;

/**
 * @author OVEA
 * 说明：题型编号，数据结构用J表示，数据库原理用K表示，网络用W表示，软件工程用R表示，操作系统用Z表示，应用基础用C表示，多媒体技术用D表示，硬件部分用Y表示，移动互联应用用H表示；C语言用1表示，C++语言用2表示，Java语言用3表示，VB用4表示，VFP用5表示；难度用0和1表示，0表示简单，1表示困难。新增部分：S表示数据表示和计算，L表示离散数学，Q表示软件知识产权，6表示C#语言。
 *
 */
public class Puzzle {
    private String id;
    private Character type;
    private String subject;
    private List<PuzzleItem> options;
    private Integer difficulty;

    @Override
    public String toString() {
        return "Puzzle{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", subject='" + subject + '\'' +
                ", options=" + options +
                ", difficulty=" + difficulty +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Character getType() {
        return type;
    }

    public void setType(Character type) {
        this.type = type;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<PuzzleItem> getOptions() {
        return options;
    }

    public void setOptions(List<PuzzleItem> options) {
        this.options = options;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Puzzle(String id, Character type, String subject, List<PuzzleItem> options, Integer difficulty) {
        this.id = id;
        this.type = type;
        this.subject = subject;
        this.options = options;
        this.difficulty = difficulty;
    }

    public Puzzle() {
    }
}
