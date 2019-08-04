package cn.oveay.cspuzzle.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * @author OVEA
 * 说明：题型编号，数据结构用J表示，数据库原理用K表示，网络用W表示，软件工程用R表示，操作系统用Z表示，应用基础用C表示，多媒体技术用D表示，硬件部分用Y表示，移动互联应用用H表示；C语言用1表示，C++语言用2表示，Java语言用3表示，VB用4表示，VFP用5表示；难度用0和1表示，0表示简单，1表示困难。新增部分：S表示数据表示和计算，L表示离散数学，Q表示软件知识产权，6表示C#语言。
 *
 */
@Getter
@Setter
@ToString
public class Puzzle {
    private String id;
    private Character type;
    private String subject;
    private List<PuzzleItem> options;
    private Integer difficulty;
}
