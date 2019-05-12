package com.smartscity.evaluate.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.smartscity.evaluate.annotation.ExcelColumn;
import com.smartscity.evaluate.domain.enumeration.*;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Evaluation.
 */
public class EvaluationPlus implements Serializable {

    private static final long serialVersionUID = 1L;



    @ExcelColumn(value = "专家", col = 1)
    private String username;


    @ExcelColumn(value = "标题", col = 2)
    private String title;

    /**
     * 主要完成单位
     */
    @ExcelColumn(value = "主要完成单位", col = 3)
    private String orgName;

    /**
     * 主要完成人
     */
    @ExcelColumn(value = "主要完成人", col = 4)
    private String actor;


    private String speaker;


    /**
     * 一等奖、二等奖、三等奖
     */
    @ExcelColumn(value = "申请奖项级别", col = 5)
    private String level;


    @ExcelColumn(value = "任务来源", col =6)
    private Integer taskSourceScore;


    @ExcelColumn(value = "主要发现和创新点", col = 7)
    private Integer discoveryAndInnovationScore;

    /**
     * 先进程度: 领先水平（21-30分）、先进水平（11-20分）、一般水平（10分或以下）
     */
    @ExcelColumn(value = "先进程度", col = 8)
    private Integer advancedLevelScore;

    /**
     * 应用与推广及其效益: 应用推广及效益显著（11-15分）、应用推广及效益明显（6-10分）、应用推广及效益一般（5分或以下）
     */
    @ExcelColumn(value = "应用与推广及其效益", col = 9)
    private Integer applicationAndPromotionScore;

    /**
     * 论文直接给分即可: 根据论文、著作和知识产权的学术水平、质量和数量的综合情况给分（5-10分）
     */
    @ExcelColumn(value = "论文评分", col = 10)
    private Integer paperScore;

    /**
     * 答辩直接给分即可: 现场答辩重点突出、简明扼要、准确无误，PPT制作美观清晰（5-10分）
     */
    @ExcelColumn(value = "答辩评分", col = 11)
    private Integer replyScore;

    @ExcelColumn(value = "总分", col = 12)
    private Integer totalScore;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getTaskSourceScore() {
        return taskSourceScore;
    }

    public void setTaskSourceScore(Integer taskSourceScore) {
        this.taskSourceScore = taskSourceScore;
    }

    public Integer getDiscoveryAndInnovationScore() {
        return discoveryAndInnovationScore;
    }

    public void setDiscoveryAndInnovationScore(Integer discoveryAndInnovationScore) {
        this.discoveryAndInnovationScore = discoveryAndInnovationScore;
    }

    public Integer getAdvancedLevelScore() {
        return advancedLevelScore;
    }

    public void setAdvancedLevelScore(Integer advancedLevelScore) {
        this.advancedLevelScore = advancedLevelScore;
    }

    public Integer getApplicationAndPromotionScore() {
        return applicationAndPromotionScore;
    }

    public void setApplicationAndPromotionScore(Integer applicationAndPromotionScore) {
        this.applicationAndPromotionScore = applicationAndPromotionScore;
    }

    public Integer getPaperScore() {
        return paperScore;
    }

    public void setPaperScore(Integer paperScore) {
        this.paperScore = paperScore;
    }

    public Integer getReplyScore() {
        return replyScore;
    }

    public void setReplyScore(Integer replyScore) {
        this.replyScore = replyScore;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
