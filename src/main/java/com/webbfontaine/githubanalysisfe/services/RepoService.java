package com.webbfontaine.githubanalysisfe.services;

import com.webbfontaine.githubanalysisfe.pojos.GithubRepo;
import com.webbfontaine.githubanalysisfe.pojos.RepoCommit;
import com.webbfontaine.githubanalysisfe.pojos.RepoOwner;
import com.webbfontaine.githubanalysisfe.pojos.RepoSearch;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RepoService {
    List<GithubRepo> getRepos();
    RepoSearch searchRepos(String query);
    List<RepoOwner> getRepoContributors(String owner, String repo);
    List<RepoCommit> getRepoCommits(String owner, String repo);
}
