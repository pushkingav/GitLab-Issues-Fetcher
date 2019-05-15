package com.ipmks.gitlab.fetcher.service;

import com.ipmks.gitlab.fetcher.gitlab.GitlabUtils;
import com.ipmks.gitlab.fetcher.model.to.IssueTo;
import org.gitlab4j.api.GitLabApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service("gitlabApiService")
public class GitlabApiServiceImpl implements GitlabApiService {
    @Value("${gitlab.api.token}")
    private String apiToken;

    @Value("${gitlab.api.host}")
    private String apiHost;

    @Value("${gitlab.api.namespace}")
    private String nameSpace;

    @Value("${gitlab.api.project}")
    private String projectName;

    private GitLabApi gitLabApi;

    @PostConstruct
    public void init() {
        gitLabApi = new GitLabApi(apiHost, apiToken);
        gitLabApi.setIgnoreCertificateErrors(true);
    }

    @Override
    public List<String> getGitlabIssuesForMilestone(String milestone) {
        return GitlabUtils.getGitlabIssuesForMilestone(gitLabApi,nameSpace, projectName, milestone);
    }

    @Override
    public List<IssueTo> getUserIssuesOfMilestone(String email, String milestone) {
        //TODO - make grouping by labels: release blocker, high, low
        return GitlabUtils.getGitlabIssuesOfUserForMilestone(gitLabApi, nameSpace, projectName, milestone, email)
                .stream().map(IssueTo::createFromIssue).collect(Collectors.toList());
    }
}
