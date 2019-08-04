package cn.oveay.cspuzzle.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PuzzleItem {
    private String id;
    private String puzzle_id;
    private Character option;
    private String content;
    private Boolean isAnswer;
}
