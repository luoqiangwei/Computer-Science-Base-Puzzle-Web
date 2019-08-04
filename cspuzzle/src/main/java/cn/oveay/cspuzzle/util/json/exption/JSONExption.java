package cn.oveay.cspuzzle.util.json.exption;

public class JSONExption extends Exception {
    public JSONExption(){}

    public JSONExption(String errname){
        super(errname);
    }
}
