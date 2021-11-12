package com.it.pojo;

/** * 建议将业务相关的成员变量类型设置为String类型，以便后面拦截器内对单元格的内容进行强转 */
public class StudentInfo {
    //学号 
    private String num;
    //姓名
    private String name;
    //总分
    private String totalScore;
    //是否进前十
    private String isTop3;
    //英语成绩
    private String englishScore;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }

    public String getIsTop3() {
        return isTop3;
    }

    public void setIsTop3(String isTop3) {
        this.isTop3 = isTop3;
    }

    public String getEnglishScore() {
        return englishScore;
    }

    public void setEnglishScore(String englishScore) {
        this.englishScore = englishScore;
    }

    public StudentInfo() {
    }

    public StudentInfo(String num, String name, String totalScore, String isTop3, String englishScore) {
        this.num = num;
        this.name = name;
        this.totalScore = totalScore;
        this.isTop3 = isTop3;
        this.englishScore = englishScore;
    }
}