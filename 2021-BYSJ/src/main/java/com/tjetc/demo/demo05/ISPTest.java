package com.tjetc.demo.demo05;

/*
 * 设计模式 --接口隔离原则
 * */
public class ISPTest {
    public static void main(String[] args) {
        InputModule inputModule = StuScoreList.getInputModule();
        CountModule countModule = StuScoreList.getCountModule();
        PrintModule printModule = StuScoreList.getPrintModule();

        inputModule.insert();
        countModule.countAverage();
        printModule.printStuInfo();
    }
}
