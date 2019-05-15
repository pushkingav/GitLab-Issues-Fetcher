package com.ipmks.gitlab.fetcher.model.to;

import org.gitlab4j.api.models.Issue;

import java.util.Collections;
import java.util.List;

public class IssueTo {
    private Integer iid;
    private String title;
    private String assignee;
    private List<String> labels;

    public IssueTo(Integer iid, String title, String assignee, List<String> labels) {
        this.iid = iid;
        this.title = title;
        this.assignee = assignee;
        this.labels = labels;
    }

    public static IssueTo createFromIssue(Issue issue) {
        return new IssueTo(issue.getIid(), issue.getTitle(), issue.getAssignee().getUsername(), issue.getLabels());
    }

    public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public List<String> getLabels() {
        return Collections.unmodifiableList(labels);
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    @Override
    public String toString() {
        return "IssueTo{" +
                "iid=" + iid +
                ", title='" + title + '\'' +
                ", assignee='" + assignee + '\'' +
                ", labels=" + labels +
                '}';
    }
}
