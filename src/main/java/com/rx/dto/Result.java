package com.rx.dto;

public class Result {
    private String batch;
    private int semester;
    private boolean result;

    public Result(String batch, int semester, boolean result) {
        this.batch = batch;
        this.semester = semester;
        this.result = result;
    }

    @Override
    public String toString() {
        return "Result{" +
                "batch='" + batch + '\'' +
                ", semester=" + semester +
                ", result=" + result +
                '}';
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
