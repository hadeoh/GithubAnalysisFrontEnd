package com.webbfontaine.githubanalysisfe.services;

import com.webbfontaine.githubanalysisfe.components.RestCaller;
import com.webbfontaine.githubanalysisfe.pojos.GithubRepo;
import com.webbfontaine.githubanalysisfe.pojos.RepoCommit;
import com.webbfontaine.githubanalysisfe.pojos.RepoOwner;
import com.webbfontaine.githubanalysisfe.pojos.RepoSearch;
import com.webbfontaine.githubanalysisfe.utils.JsonConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RepoServiceImpl implements RepoService {

    private final RestCaller restCaller;

    @Value("${github.analysis.be.url}")
    private String githubUrl;

    @Override
    public List<GithubRepo> getRepos() {
        List<GithubRepo> repos = new ArrayList<>();
        try {
            String result = restCaller.makeRequest(githubUrl, null, HttpMethod.GET);
            repos = Arrays.asList(JsonConverter.toObj(result, GithubRepo[].class));
        } catch (Exception e) {
            log.error("An error occurred while getting the list of repositories due to {}", e.getMessage());
        }
        return repos;
    }

    @Override
    public RepoSearch searchRepos(String query) {
        RepoSearch repos = new RepoSearch();
        String url = githubUrl + "/search";
        try {
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("query", query)
                    .queryParam("sort", "stars")
                    .queryParam("order", "desc")
                    .queryParam("perPage", 100)
                    .build();
            String result = restCaller.makeRequest(builder.toUriString(), null, HttpMethod.GET);
            repos = JsonConverter.toObj(result, RepoSearch.class);
        } catch (Exception e) {
            log.error("An error occurred while searching for repositories due to {}", e.getMessage());
        }
        return repos;
    }

    @Override
    public List<RepoOwner> getRepoContributors(String owner, String repo) {
        List<RepoOwner> contributors = new ArrayList<>();
        String url = githubUrl + "/users/" + owner + "/" + repo;
        try {
            String result = restCaller.makeRequest(url, null, HttpMethod.GET);
            contributors = Arrays.asList(JsonConverter.toObj(result, RepoOwner[].class));
        } catch (Exception e) {
            log.error("An error occurred while fetching the contributors of the repo due to {}", e.getMessage());
        }
        return contributors;
    }

    @Override
    public List<RepoCommit> getRepoCommits(String owner, String repo) {
        List<RepoCommit> commits = new ArrayList<>();
        String url = githubUrl + "/commits/" + owner + "/" + repo;
        try {
            String result = restCaller.makeRequest(url, null, HttpMethod.GET);
            commits = Arrays.asList(JsonConverter.toObj(result, RepoCommit[].class));
        } catch (Exception e) {
            log.error("An error occurred while fetching the commits of the repo due to {}", e.getMessage());
        }
        return commits;
    }
}
