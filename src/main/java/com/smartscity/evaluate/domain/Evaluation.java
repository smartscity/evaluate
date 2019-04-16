package com.smartscity.evaluate.domain;


import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.smartscity.evaluate.domain.enumeration.Level;

import com.smartscity.evaluate.domain.enumeration.TaskSource;

import com.smartscity.evaluate.domain.enumeration.DiscoveryAndInnovation;

import com.smartscity.evaluate.domain.enumeration.AdvancedLevel;

import com.smartscity.evaluate.domain.enumeration.ApplicationAndPromotion;

/**
 * A Evaluation.
 */
@Entity
@Table(name = "evaluation")
public class Evaluation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "speaker_id")
    private Integer speakerId;

    @Column(name = "title")
    private String title;

    /**
     * 主要完成单位
     */
    @ApiModelProperty(value = "主要完成单位")
    @Column(name = "org_name")
    private String orgName;

    /**
     * 主要完成人
     */
    @ApiModelProperty(value = "主要完成人")
    @Column(name = "actor")
    private String actor;

    /**
     * 演讲者
     */
    @ApiModelProperty(value = "演讲者")
    @Column(name = "speaker")
    private String speaker;

    /**
     * 一等奖、二等奖、三等奖
     */
    @ApiModelProperty(value = "一等奖、二等奖、三等奖")
    @Enumerated(EnumType.STRING)
    @Column(name = "jhi_level")
    private Level level;

    /**
     * 任务来源: 国家计划（4-5分）、部委计划、省（自治区、市）计划（2-3分）、中国人民解放军计划（2-3分）、基金资助（2-3分）、国际合作、其他企事业单位委托(1)、自选(1)、其他(1)。
     */
    @ApiModelProperty(value = "任务来源: 国家计划（4-5分）、部委计划、省（自治区、市）计划（2-3分）、中国人民解放军计划（2-3分）、基金资助（2-3分）、国际合作、其他企事业单位委托(1)、自选(1)、其他(1)。")
    @Enumerated(EnumType.STRING)
    @Column(name = "task_source")
    private TaskSource taskSource;

    @Column(name = "task_source_score")
    private Integer taskSourceScore;

    /**
     * 主要发现和创新点: 重大发现和显著创新（21-30分）、重要发现和明显创新（11-20分）、新发现和一般创新（10分或以下）
     */
    @ApiModelProperty(value = "主要发现和创新点: 重大发现和显著创新（21-30分）、重要发现和明显创新（11-20分）、新发现和一般创新（10分或以下）")
    @Enumerated(EnumType.STRING)
    @Column(name = "discovery_and_innovation")
    private DiscoveryAndInnovation discoveryAndInnovation;

    @Column(name = "discovery_and_innovation_score")
    private Integer discoveryAndInnovationScore;

    /**
     * 先进程度: 领先水平（21-30分）、先进水平（11-20分）、一般水平（10分或以下）
     */
    @ApiModelProperty(value = "先进程度: 领先水平（21-30分）、先进水平（11-20分）、一般水平（10分或以下）")
    @Enumerated(EnumType.STRING)
    @Column(name = "advanced_level")
    private AdvancedLevel advancedLevel;

    @Column(name = "advanced_level_score")
    private Integer advancedLevelScore;

    /**
     * 应用与推广及其效益: 应用推广及效益显著（11-15分）、应用推广及效益明显（6-10分）、应用推广及效益一般（5分或以下）
     */
    @ApiModelProperty(value = "应用与推广及其效益: 应用推广及效益显著（11-15分）、应用推广及效益明显（6-10分）、应用推广及效益一般（5分或以下）")
    @Enumerated(EnumType.STRING)
    @Column(name = "application_and_promotion")
    private ApplicationAndPromotion applicationAndPromotion;

    @Column(name = "application_and_promotion_score")
    private Integer applicationAndPromotionScore;

    /**
     * 论文直接给分即可: 根据论文、著作和知识产权的学术水平、质量和数量的综合情况给分（5-10分）
     */
    @ApiModelProperty(value = "论文直接给分即可: 根据论文、著作和知识产权的学术水平、质量和数量的综合情况给分（5-10分）")
    @Column(name = "paper_score")
    private Integer paperScore;

    /**
     * 答辩直接给分即可: 现场答辩重点突出、简明扼要、准确无误，PPT制作美观清晰（5-10分）
     */
    @ApiModelProperty(value = "答辩直接给分即可: 现场答辩重点突出、简明扼要、准确无误，PPT制作美观清晰（5-10分）")
    @Column(name = "reply_score")
    private Integer replyScore;

    @Column(name = "total_score")
    private Integer totalScore;

    @Column(name = "avg_score")
    private Double avgScore;

    @Column(name = "create_time")
    private Instant createTime;

    @Column(name = "update_time")
    private Instant updateTime;

    @Column(name = "user_id")
    private String userId;

    /**
     * 评价者
     */
    @ApiModelProperty(value = "评价者")
    @Column(name = "evaluator")
    private String evaluator;

    @Column(name = "remark")
    private String remark;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSpeakerId() {
        return speakerId;
    }

    public Evaluation speakerId(Integer speakerId) {
        this.speakerId = speakerId;
        return this;
    }

    public void setSpeakerId(Integer speakerId) {
        this.speakerId = speakerId;
    }

    public String getTitle() {
        return title;
    }

    public Evaluation title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrgName() {
        return orgName;
    }

    public Evaluation orgName(String orgName) {
        this.orgName = orgName;
        return this;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getActor() {
        return actor;
    }

    public Evaluation actor(String actor) {
        this.actor = actor;
        return this;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getSpeaker() {
        return speaker;
    }

    public Evaluation speaker(String speaker) {
        this.speaker = speaker;
        return this;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public Level getLevel() {
        return level;
    }

    public Evaluation level(Level level) {
        this.level = level;
        return this;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public TaskSource getTaskSource() {
        return taskSource;
    }

    public Evaluation taskSource(TaskSource taskSource) {
        this.taskSource = taskSource;
        return this;
    }

    public void setTaskSource(TaskSource taskSource) {
        this.taskSource = taskSource;
    }

    public Integer getTaskSourceScore() {
        return taskSourceScore;
    }

    public Evaluation taskSourceScore(Integer taskSourceScore) {
        this.taskSourceScore = taskSourceScore;
        return this;
    }

    public void setTaskSourceScore(Integer taskSourceScore) {
        this.taskSourceScore = taskSourceScore;
    }

    public DiscoveryAndInnovation getDiscoveryAndInnovation() {
        return discoveryAndInnovation;
    }

    public Evaluation discoveryAndInnovation(DiscoveryAndInnovation discoveryAndInnovation) {
        this.discoveryAndInnovation = discoveryAndInnovation;
        return this;
    }

    public void setDiscoveryAndInnovation(DiscoveryAndInnovation discoveryAndInnovation) {
        this.discoveryAndInnovation = discoveryAndInnovation;
    }

    public Integer getDiscoveryAndInnovationScore() {
        return discoveryAndInnovationScore;
    }

    public Evaluation discoveryAndInnovationScore(Integer discoveryAndInnovationScore) {
        this.discoveryAndInnovationScore = discoveryAndInnovationScore;
        return this;
    }

    public void setDiscoveryAndInnovationScore(Integer discoveryAndInnovationScore) {
        this.discoveryAndInnovationScore = discoveryAndInnovationScore;
    }

    public AdvancedLevel getAdvancedLevel() {
        return advancedLevel;
    }

    public Evaluation advancedLevel(AdvancedLevel advancedLevel) {
        this.advancedLevel = advancedLevel;
        return this;
    }

    public void setAdvancedLevel(AdvancedLevel advancedLevel) {
        this.advancedLevel = advancedLevel;
    }

    public Integer getAdvancedLevelScore() {
        return advancedLevelScore;
    }

    public Evaluation advancedLevelScore(Integer advancedLevelScore) {
        this.advancedLevelScore = advancedLevelScore;
        return this;
    }

    public void setAdvancedLevelScore(Integer advancedLevelScore) {
        this.advancedLevelScore = advancedLevelScore;
    }

    public ApplicationAndPromotion getApplicationAndPromotion() {
        return applicationAndPromotion;
    }

    public Evaluation applicationAndPromotion(ApplicationAndPromotion applicationAndPromotion) {
        this.applicationAndPromotion = applicationAndPromotion;
        return this;
    }

    public void setApplicationAndPromotion(ApplicationAndPromotion applicationAndPromotion) {
        this.applicationAndPromotion = applicationAndPromotion;
    }

    public Integer getApplicationAndPromotionScore() {
        return applicationAndPromotionScore;
    }

    public Evaluation applicationAndPromotionScore(Integer applicationAndPromotionScore) {
        this.applicationAndPromotionScore = applicationAndPromotionScore;
        return this;
    }

    public void setApplicationAndPromotionScore(Integer applicationAndPromotionScore) {
        this.applicationAndPromotionScore = applicationAndPromotionScore;
    }

    public Integer getPaperScore() {
        return paperScore;
    }

    public Evaluation paperScore(Integer paperScore) {
        this.paperScore = paperScore;
        return this;
    }

    public void setPaperScore(Integer paperScore) {
        this.paperScore = paperScore;
    }

    public Integer getReplyScore() {
        return replyScore;
    }

    public Evaluation replyScore(Integer replyScore) {
        this.replyScore = replyScore;
        return this;
    }

    public void setReplyScore(Integer replyScore) {
        this.replyScore = replyScore;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public Evaluation totalScore(Integer totalScore) {
        this.totalScore = totalScore;
        return this;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Double getAvgScore() {
        return avgScore;
    }

    public Evaluation avgScore(Double avgScore) {
        this.avgScore = avgScore;
        return this;
    }

    public void setAvgScore(Double avgScore) {
        this.avgScore = avgScore;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public Evaluation createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public Evaluation updateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserId() {
        return userId;
    }

    public Evaluation userId(String userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEvaluator() {
        return evaluator;
    }

    public Evaluation evaluator(String evaluator) {
        this.evaluator = evaluator;
        return this;
    }

    public void setEvaluator(String evaluator) {
        this.evaluator = evaluator;
    }

    public String getRemark() {
        return remark;
    }

    public Evaluation remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Evaluation)) {
            return false;
        }
        return id != null && id.equals(((Evaluation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Evaluation{" +
            "id=" + getId() +
            ", speakerId=" + getSpeakerId() +
            ", title='" + getTitle() + "'" +
            ", orgName='" + getOrgName() + "'" +
            ", actor='" + getActor() + "'" +
            ", speaker='" + getSpeaker() + "'" +
            ", level='" + getLevel() + "'" +
            ", taskSource='" + getTaskSource() + "'" +
            ", taskSourceScore=" + getTaskSourceScore() +
            ", discoveryAndInnovation='" + getDiscoveryAndInnovation() + "'" +
            ", discoveryAndInnovationScore=" + getDiscoveryAndInnovationScore() +
            ", advancedLevel='" + getAdvancedLevel() + "'" +
            ", advancedLevelScore=" + getAdvancedLevelScore() +
            ", applicationAndPromotion='" + getApplicationAndPromotion() + "'" +
            ", applicationAndPromotionScore=" + getApplicationAndPromotionScore() +
            ", paperScore=" + getPaperScore() +
            ", replyScore=" + getReplyScore() +
            ", totalScore=" + getTotalScore() +
            ", avgScore=" + getAvgScore() +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", userId='" + getUserId() + "'" +
            ", evaluator='" + getEvaluator() + "'" +
            ", remark='" + getRemark() + "'" +
            "}";
    }
}
