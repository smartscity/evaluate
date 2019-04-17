package com.smartscity.evaluate.domain;


import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

import com.smartscity.evaluate.domain.enumeration.Level;

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

    @Lob
    @Column(name = "icon")
    private byte[] icon;

    @Column(name = "icon_content_type")
    private String iconContentType;

    @Lob
    @Column(name = "pdf")
    private byte[] pdf;

    @Column(name = "pdf_content_type")
    private String pdfContentType;

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

    public byte[] getIcon() {
        return icon;
    }

    public Speaker icon(byte[] icon) {
        this.icon = icon;
        return this;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

    public String getIconContentType() {
        return iconContentType;
    }

    public Speaker iconContentType(String iconContentType) {
        this.iconContentType = iconContentType;
        return this;
    }

    public void setIconContentType(String iconContentType) {
        this.iconContentType = iconContentType;
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
            ", icon='" + getIcon() + "'" +
            ", iconContentType='" + getIconContentType() + "'" +
            ", pdf='" + getPdf() + "'" +
            ", pdfContentType='" + getPdfContentType() + "'" +
            "}";
    }
}
