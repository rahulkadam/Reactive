package com.rx.memory;

public class AnalysisDTO {
    private String str;

    public AnalysisDTO(int i) {
        this.str = String.valueOf(i);
    }
    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
