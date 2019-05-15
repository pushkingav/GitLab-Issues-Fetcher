package com.ipmks.gitlab.fetcher.gitlab;

import org.gitlab4j.api.Constants;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Issue;
import org.gitlab4j.api.models.Milestone;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GitlabUtils {
    private static final Logger logger = LoggerFactory.getLogger(GitlabUtils.class);

    public static List<String> getGitlabIssuesForMilestone(GitLabApi gitLabApi, String nameSpace, String projectName, String milestone) {
        List<String> result = null;
        try {
            Project project = gitLabApi.getProjectApi().getProject(nameSpace, projectName);
            logger.info("Found project - " + project.getName());
            Milestone foundMilestone = getGitlabMilestone(gitLabApi,project.getId(), milestone);

            List<Issue> issues;
            if (foundMilestone != null) {
                logger.info(String.format("Issues for milestone %s found", foundMilestone.getTitle()));
                issues = gitLabApi.getMilestonesApi().getIssues(project.getId(), foundMilestone.getId());
                if (issues.size() > 0) {
                    logger.info(String.format("Processing %d issues. This includes both closed and opened", issues.size()));
                }
                //Sorting by ticket number ascending
                logger.info("Listing closed issues:");
                issues.sort(Comparator.comparing(Issue::getIid));
                result = issues.stream()
                        .filter(issue -> issue.getState().equals(Constants.IssueState.CLOSED))
                        .map(issue -> issue.getIid() + " " + issue.getTitle())
                        .collect(Collectors.toList());
            }
        } catch (GitLabApiException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<Issue> getGitlabIssuesOfUserForMilestone(GitLabApi gitLabApi, String nameSpace, String projectName,
                                                                 String milestone, String email) {
        List<Issue> userIssues = null;
        try {
            List<User> users = gitLabApi.getUserApi().findUsers(email);
            Project project = gitLabApi.getProjectApi().getProject(nameSpace, projectName);
            logger.info("Found project - " + project.getName());
            Milestone foundMilestone = getGitlabMilestone(gitLabApi,project.getId(), milestone);
            List<Issue> issues = gitLabApi.getMilestonesApi().getIssues(project.getId(), foundMilestone.getId());
            userIssues = issues.stream()
                    .filter(issue -> issue.getAssignee().getId().equals(users.get(0).getId()))
                    .collect(Collectors.toList());
        } catch (GitLabApiException e) {
            e.printStackTrace();
        }
        return userIssues;
    }

    private static Milestone getGitlabMilestone(GitLabApi gitLabApi, Integer projectId, String milestone) {
        Milestone found = null;
        try {
            List<Milestone> milestones = gitLabApi.getMilestonesApi().getMilestones(projectId);
            logger.info("Got milestones. Processing...");
            for (Milestone current : milestones) {
                if (current.getTitle().equals(milestone)) {
                    found = current;
                }
            }
        } catch (GitLabApiException e) {
            e.printStackTrace();
        }
        if (found == null) {
            logger.warn("No milestone found for name: " + milestone);
        }
        return found;
    }
}
