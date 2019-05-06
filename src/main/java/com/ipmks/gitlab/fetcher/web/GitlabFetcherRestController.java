package com.ipmks.gitlab.fetcher.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan
@RestController
public class GitlabFetcherRestController {
    private static final String FETCHER_REST_URL = "/fetcher";
}
