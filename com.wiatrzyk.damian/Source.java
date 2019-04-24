package com.wiatrzyk.damian

public class Source {

    private byte[] source;
    private int type;

    public Source(byte[] source, int type){
        this.source = source;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public byte[] getSource() {
        return source;
    }

    public void setSource(byte[] source) {
        this.source = source;
    }

    public void setType(int type) {
        this.type = type;
    }
}
