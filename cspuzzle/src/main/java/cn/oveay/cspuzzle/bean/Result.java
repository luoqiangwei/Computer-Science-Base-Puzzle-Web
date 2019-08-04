package cn.oveay.cspuzzle.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class Result {
    private String id;
    private String user_id;
    private List<ResultItem> answers;
    private Date resultDate;
    private Double grade;
}
