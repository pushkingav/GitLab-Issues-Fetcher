package com.ipmks.gitlab.fetcher.service;

import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.models.Issue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service("gitlabApiService")
public class GitlabApiServiceImpl implements GitlabApiService {
    @Value("${gitlab.api.token}")
    private String apiToken;

    @Value("${gitlab.api.host}")
    private String apiHost;

    private GitLabApi gitLabApi;

    @PostConstruct
    public void init() {
        gitLabApi = new GitLabApi(apiHost, apiToken);
        gitLabApi.setIgnoreCertificateErrors(true);
    }

    @Override
    public List<String> getGitlabIssuesForMilestone(String milestone) {
        return null;
    }

    @Override
    public List<Issue> getUserIssuesOfMilestone(String email, String milestone) {
        return null;
    }
}
