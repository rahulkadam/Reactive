package com.rx.dto;

public class College {
    private String name;
    private long id;
    private long rank;

    public College(String name, long id, long rank) {
        this.name = name;
        this.id = id;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "College{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", rank=" + rank +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRank() {
        return rank;
    }

    public void setRank(long rank) {
        this.rank = rank;
    }
}
