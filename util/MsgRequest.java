package util;

import java.io.Serializable;

public class MsgRequest implements Serializable {
    private int value1, value2;

    public MsgRequest(int value1, int value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public int getValue1() {
        return value1;
    }

    public int getValue2() {
        return value2;
    }        
}
