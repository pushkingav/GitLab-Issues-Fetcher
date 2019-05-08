package com.ipmks.gitlab.fetcher;

import com.ipmks.gitlab.fetcher.gitlab.GitlabUtils;

import java.util.List;

//args[0] = private token, args[1] = milestone
public class GitlabFetcherConsoleApplication {
    public static void main(String[] args) {
        if (args.length < 2) {
            throw new RuntimeException("Too few command line arguments. Usage: java -jar gitlab-issues-tracker-0.1.0.jar MILESTONE_NAME");
        }
        List<String> issues = GitlabUtils.getGitlabIssuesForMilestone("gitlab.api.token", args[0]);
        issues.forEach(System.out::println);
    }
}
