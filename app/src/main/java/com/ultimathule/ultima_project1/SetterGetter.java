package com.ultimathule.ultima_project1;

/**
 * Created by nobuyukiizumi on 15/10/19.
 */
public class SetterGetter {

    private int id;
    private String state;
    private int complete_flg;

    public SetterGetter(){}

    public SetterGetter(String state, int complete_flg) {
        super();
        this.state = state;
        this.complete_flg = complete_flg;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getState() {

        return state;
    }

    public void setState(String state) {

        this.state = state;
    }

    public int getComplete() {

        return complete_flg;
    }
    public void setComplete(int complete_flg) {

        this.complete_flg = complete_flg;
    }

    @Override
    public String toString() {

        return "SetterGetter [id= " + id + ", state= " + state + ", complete_flg= " + complete_flg
                + "]";
    }
}

