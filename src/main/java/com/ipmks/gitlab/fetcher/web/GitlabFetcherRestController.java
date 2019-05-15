package com.ipmks.gitlab.fetcher.web;

import com.ipmks.gitlab.fetcher.model.to.IssueTo;
import com.ipmks.gitlab.fetcher.service.GitlabApiService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private GitlabApiService gitlabApiService;

    @GetMapping("/issues/{milestone}")
    public List<String> getIssuesForMilestone(@PathVariable("milestone") String milestone) {
        return gitlabApiService.getGitlabIssuesForMilestone(milestone);
    }

    @GetMapping("/issues/{milestone}/{email}")
    public List<IssueTo> getUserIssuesOfMilestone(@PathVariable("milestone") String milestone, @PathVariable("email") String email) {
        return gitlabApiService.getUserIssuesOfMilestone(email, milestone);
    }
}
