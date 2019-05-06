package com.ipmks.gitlab.fetcher;

import org.gitlab4j.api.Constants;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Issue;
import org.gitlab4j.api.models.Milestone;
import org.gitlab4j.api.models.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;

//args[0] = private token, args[1] = milestone
public class GitlabFetcherApplication {
    private static final Logger logger = LoggerFactory.getLogger(GitlabFetcherApplication.class);

    public static void main(String[] args) {
        if (args.length < 2) {
            throw new RuntimeException("Too few command line arguments. Usage: java -jar gitlab-issues-tracker-0.1.0.jar YOUR_PRIVATE_TOKEN MILESTONE_NAME");
        }
        GitLabApi gitLabApi = new GitLabApi("https://192.168.1.10", args[0]);
        gitLabApi.setIgnoreCertificateErrors(true);
        try {
            Project project = gitLabApi.getProjectApi().getProject("rational-enterprise", "rational-governance");
            logger.info("Found project - " + project.getName());
            List<Milestone> milestones = gitLabApi.getMilestonesApi().getMilestones(project.getId());
            logger.info("Got milestones. Processing...");
            Milestone found  = null;
            for (Milestone current : milestones) {
                if (current.getTitle().equals(args[1])) {
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
                issues.stream()
                        .filter(issue -> issue.getState().equals(Constants.IssueState.CLOSED))
                        .forEach(issue -> System.out.println(issue.getIid()+ " " + issue.getTitle()));
            }
        } catch (GitLabApiException e) {
            e.printStackTrace();
        }
    }
}
