package cn.oveay.cspuzzle.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResultItem {
    private String id;
    private String result_id;
    private String puzzle_id;
    private String option;
}
