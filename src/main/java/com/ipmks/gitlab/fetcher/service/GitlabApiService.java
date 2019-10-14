package com.ipmks.gitlab.fetcher.service;


import com.ipmks.gitlab.fetcher.model.to.IssueTo;
import org.gitlab4j.api.models.Milestone;

import java.util.List;

public interface GitlabApiService {

    List<String> getGitlabIssuesForMilestone(String milestone);

    List<IssueTo> getUserIssuesOfMilestone(String email, String milestone);

    List<Milestone> getMilestones(String startingWith);

}
