package com.smartscity.evaluate.domain;


import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

import com.smartscity.evaluate.domain.enumeration.Level;

import com.smartscity.evaluate.domain.enumeration.Review;

/**
 * A Speaker.
 */
@Entity
@Table(name = "speaker")
public class Speaker implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "jhi_level")
    private Level level;

    /**
     * 上传附件
     */
    @ApiModelProperty(value = "上传附件")
    @Lob
    @Column(name = "pdf")
    private byte[] pdf;

    @Column(name = "pdf_content_type")
    private String pdfContentType;

    /**
     * 附件地址
     */
    @ApiModelProperty(value = "附件地址")
    @Column(name = "path")
    private String path;

    /**
     * 本地文件
     */
    @ApiModelProperty(value = "本地文件")
    @Column(name = "local_path")
    private String localPath;

    @Enumerated(EnumType.STRING)
    @Column(name = "review")
    private Review review;

    /**
     * 审核备注
     */
    @ApiModelProperty(value = "审核备注")
    @Lob
    @Column(name = "remark")
    private String remark;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Speaker title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrgName() {
        return orgName;
    }

    public Speaker orgName(String orgName) {
        this.orgName = orgName;
        return this;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getActor() {
        return actor;
    }

    public Speaker actor(String actor) {
        this.actor = actor;
        return this;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getSpeaker() {
        return speaker;
    }

    public Speaker speaker(String speaker) {
        this.speaker = speaker;
        return this;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public Level getLevel() {
        return level;
    }

    public Speaker level(Level level) {
        this.level = level;
        return this;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public byte[] getPdf() {
        return pdf;
    }

    public Speaker pdf(byte[] pdf) {
        this.pdf = pdf;
        return this;
    }

    public void setPdf(byte[] pdf) {
        this.pdf = pdf;
    }

    public String getPdfContentType() {
        return pdfContentType;
    }

    public Speaker pdfContentType(String pdfContentType) {
        this.pdfContentType = pdfContentType;
        return this;
    }

    public void setPdfContentType(String pdfContentType) {
        this.pdfContentType = pdfContentType;
    }

    public String getPath() {
        return path;
    }

    public Speaker path(String path) {
        this.path = path;
        return this;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getLocalPath() {
        return localPath;
    }

    public Speaker localPath(String localPath) {
        this.localPath = localPath;
        return this;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public Review getReview() {
        return review;
    }

    public Speaker review(Review review) {
        this.review = review;
        return this;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public String getRemark() {
        return remark;
    }

    public Speaker remark(String remark) {
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
        if (!(o instanceof Speaker)) {
            return false;
        }
        return id != null && id.equals(((Speaker) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Speaker{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", orgName='" + getOrgName() + "'" +
            ", actor='" + getActor() + "'" +
            ", speaker='" + getSpeaker() + "'" +
            ", level='" + getLevel() + "'" +
            ", pdf='" + getPdf() + "'" +
            ", pdfContentType='" + getPdfContentType() + "'" +
            ", path='" + getPath() + "'" +
            ", localPath='" + getLocalPath() + "'" +
            ", review='" + getReview() + "'" +
            ", remark='" + getRemark() + "'" +
            "}";
    }
}
