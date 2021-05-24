package com.tjetc.util;

public enum ErrorEnumMessage {
    COMMONERRORS(100,"请按模板填写表格；"),
    CELLEMPTY(101,"单元格为空；"),
    CODEVAILD(102,"该码号已存在；"),
    CODEVAILD2(103,"该码号在分配表中找不到；"),  //发送量表中的码号可以从分配表中找到
    CELLTYPE(104,"码号应为正整数类型；"),        //单元格类型为空
	ROWEMPTY(105,"该行为空行；"),
	PARTYCODEVAILD(106,"该SP代码在分配表中找不到；"),
    SPAMMSGREPEAT(107,"该行SP代码与账期与信息类型的记录在库中已存在；"),
    SENDMSGREPEAT(108,"该行码号与账期与信息类型的记录在库中已存在；"),
    EMPITYTEMPLATE(109,"上传异常，请检查；"),
    EXCELCODEUNIQUE(110,"Excel数据中码号重复；"),
    EXCELSPCODEANDPERIODUNIQUE(111,"Excel数据中SP代码与账期与信息类型重复；"),
    EXCELCODEANDPERIODUNIQUE(112,"Excel数据中码号与账期与信息类型重复；"),
    EXCELSPCODEONEROWDIFFERENT(113,"Excel中数据不一致；"),
    DBSPCODEONEROWDIFFERENT(114,"与库中数据不一致；"),
    NOTFOUNDMDMCODEL (115,"根据Sp代码找不到关联合作方；"), ;

    private Integer errorCode;
    private String errorDescribe;

    ErrorEnumMessage() {}

    ErrorEnumMessage(Integer errorCode, String errorDescribe) {
        this.errorCode = errorCode;
        this.errorDescribe = errorDescribe;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescribe() {
        return errorDescribe;
    }

    public void setErrorDescribe(String errorDescribe) {
        this.errorDescribe = errorDescribe;
    }
}
