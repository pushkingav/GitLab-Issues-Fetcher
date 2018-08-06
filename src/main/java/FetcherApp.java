import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import pojo.GitlabIssue;

import java.util.ArrayList;
import java.util.List;

public class FetcherApp {
    private static final Logger logger = LoggerFactory.getLogger(FetcherApp.class);
    private static String urlStr = "https://gitlab.ipmks.off/api/v4/issues?scope=all&milestone=4.7.29-DEV5&per_page=50";

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<List<GitlabIssue>> entity = new HttpEntity<>(new ArrayList<>(), httpHeaders);
        httpHeaders.add("Private-Token", args[0]);
        restTemplate.exchange(urlStr, HttpMethod.GET, entity, ArrayList.class);
        List<GitlabIssue> issues = restTemplate.getForObject("https://gitlab.ipmks.off/api/v4/issues?scope=all&milestone=4.7.29-DEV5&per_page=50", ArrayList.class);
        System.out.println(1);
    }
}
