package com.wipro.newsapp.userservice.model;


import javax.persistence.*;

@Entity
@Table
public class Interaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int interactionId;
    private String ediTitle;
    private String email;
    private String report;
    private boolean likeStatus;

    public Interaction(int interactionId, String ediTitle, String email, String report, boolean likeStatus) {
        this.interactionId = interactionId;
        this.ediTitle = ediTitle;
        this.email = email;
        this.report = report;
        this.likeStatus = likeStatus;
    }

    public Interaction() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public boolean getLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(boolean likeStatus) {
        this.likeStatus = likeStatus;
    }

    public int getInteractionId() {
        return interactionId;
    }

    public void setInteractionId(int interactionId) {
        this.interactionId = interactionId;
    }

    public String getEdiTitle() {
        return ediTitle;
    }

    public void setEdiTitle(String ediTitle) {
        this.ediTitle = ediTitle;
    }
}
