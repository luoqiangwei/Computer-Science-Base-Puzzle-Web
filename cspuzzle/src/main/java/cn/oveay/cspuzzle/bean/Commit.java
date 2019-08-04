package cn.oveay.cspuzzle.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Commit {
    private String id;
    private String user_id;
    private String puzzle_id;
    private String content;
    private Integer floor;
    private Integer subFloor;
    private String reCall_user_id;
}
