package cn.ovea_y.puzzle.util.json.exption;

public class JSONExption extends Exception {
    public JSONExption(){}

    public JSONExption(String errname){
        super(errname);
    }
}
