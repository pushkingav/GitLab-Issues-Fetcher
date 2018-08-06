import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Issue;
import org.gitlab4j.api.models.Milestone;
import org.gitlab4j.api.models.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

//args[0] = token, args[1] = milestone
public class FetcherApp {
    private static final Logger logger = LoggerFactory.getLogger(FetcherApp.class);

    public static void main(String[] args) {
        GitLabApi gitLabApi = new GitLabApi("https://192.168.1.10", args[0]);
        gitLabApi.setIgnoreCertificateErrors(true);
        try {
            Project project = gitLabApi.getProjectApi().getProject("rational-enterprise", "rational-governance");
            List<Milestone> milestones = gitLabApi.getMilestonesApi().getMilestones(project.getId());
            Milestone found  = null;
            for (Milestone current : milestones) {
                if (current.getTitle().equals(args[1])) {
                    found = current;
                }
            }
            List<Issue> issues;
            if (found != null) {
                issues = gitLabApi.getMilestonesApi().getIssues(project.getId(), found.getId());
                issues.forEach(issue -> System.out.println(issue.getIid()+ "   " + issue.getTitle()));
            }

        } catch (GitLabApiException e) {
            logger.error(e.getMessage());
        }
    }
}
