package org.android.soldesk.intro_slide.vo;

/**
 * Created by shin on 2017-05-10.
 */

public class Word {
    private String name;
    private int fname, sname, wordcheck,chap,language,clear;


    @Override
    public String toString() {
        return "Word{" +
                "name='" + name + '\'' +
                ", fname=" + fname +
                ", sname=" + sname +
                ", wordcheck=" + wordcheck +
                ", chap=" + chap +
                ", language=" + language +
                ", clear=" + clear +
                '}';
    }

    public Word() {

    }

    public Word(String name, int fname, int sname,int chap,int language) {
        this.name = name;
        this.fname = fname;
        this.sname = sname;
        this.chap = chap;
        this.language=language;
    }

    public int getClear() {
        return clear;
    }

    public void setClear(int clear) {
        this.clear = clear;
    }

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public int getWordcheck() {
        return wordcheck;
    }

    public void setWordcheck(int wordcheck) {
        this.wordcheck = wordcheck;
    }

    public int getChap() {
        return chap;
    }

    public void setChap(int chap) {
        this.chap = chap;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFname() {
        return fname;
    }

    public void setFname(int fname) {
        this.fname = fname;
    }

    public int getSname() {
        return sname;
    }

    public void setSname(int sname) {
        this.sname = sname;
    }
}
