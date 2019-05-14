package com.ipmks.gitlab.fetcher.service;


import org.gitlab4j.api.models.Issue;

import java.util.List;

public interface GitlabApiService {

    List<String> getGitlabIssuesForMilestone(String milestone);

    List<Issue> getUserIssuesOfMilestone(String email, String milestone);

}
