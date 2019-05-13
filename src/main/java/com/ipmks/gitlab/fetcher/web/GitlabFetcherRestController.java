package com.ipmks.gitlab.fetcher.web;

import com.ipmks.gitlab.fetcher.gitlab.GitlabUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@ComponentScan
@RestController
public class GitlabFetcherRestController {
    private static final String FETCHER_REST_URL = "/fetcher";

    @Value("${gitlab.api.token}")
    private String apiToken;

    @Value("${gitlab.api.host}")
    private String apiHost;

    @GetMapping("/issues/{milestone}")
    public List<String> getIssuesForMilestone(@PathVariable("milestone") String milestone) {
        return GitlabUtils.getGitlabIssuesForMilestone(apiHost, apiToken, milestone);
    }
}
