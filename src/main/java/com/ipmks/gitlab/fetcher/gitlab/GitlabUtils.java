package com.ipmks.gitlab.fetcher.gitlab;

import org.gitlab4j.api.Constants;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Issue;
import org.gitlab4j.api.models.Milestone;
import org.gitlab4j.api.models.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GitlabUtils {
    private static final Logger logger = LoggerFactory.getLogger(GitlabUtils.class);

    public static List<String> getGitlabIssuesForMilestone(@NotNull String apiHost, @NotNull String token, @NotNull String milestone) {
        GitLabApi gitLabApi = new GitLabApi(apiHost, token);
        gitLabApi.setIgnoreCertificateErrors(true);
        List<String> result = null;
        try {
            Project project = gitLabApi.getProjectApi().getProject("rational-enterprise", "rational-governance");
            logger.info("Found project - " + project.getName());
            List<Milestone> milestones = gitLabApi.getMilestonesApi().getMilestones(project.getId());
            logger.info("Got milestones. Processing...");
            Milestone found  = null;
            for (Milestone current : milestones) {
                if (current.getTitle().equals(milestone)) {
                    found = current;
                }
            }
            List<Issue> issues;

            if (found != null) {
                logger.info(String.format("Issues for milestone %s found", found.getTitle()));
                issues = gitLabApi.getMilestonesApi().getIssues(project.getId(), found.getId());
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
}
