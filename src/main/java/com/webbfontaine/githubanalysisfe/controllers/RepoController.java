package com.webbfontaine.githubanalysisfe.controllers;

import com.webbfontaine.githubanalysisfe.pojos.GithubRepo;
import com.webbfontaine.githubanalysisfe.pojos.RepoCommit;
import com.webbfontaine.githubanalysisfe.pojos.RepoOwner;
import com.webbfontaine.githubanalysisfe.pojos.RepoSearch;
import com.webbfontaine.githubanalysisfe.services.RepoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/repos")
public class RepoController {

    private final RepoService repoService;

    @GetMapping
    public String getRepos(Model model) {
        List<GithubRepo> repos = repoService.getRepos();
        model.addAttribute("repos", repos);
        return "home";
    }

    @GetMapping("/search")
    public String getSearchPage() {
        return "search";
    }

    @GetMapping("/search-results")
    public @ResponseBody ResponseEntity<RepoSearch> getSearchResults(@RequestParam String query) {
        RepoSearch repos = repoService.searchRepos(query);
        return new ResponseEntity<>(repos, HttpStatus.OK);
    }

    @GetMapping("/history/{owner}/{repo}")
    public String getRepoContributors(Model model, @PathVariable("owner") String owner, @PathVariable("repo") String repo) {
        List<RepoOwner> contributors = repoService.getRepoContributors(owner, repo);
        List<RepoCommit> commits = repoService.getRepoCommits(owner, repo);
        Map<String, Integer> userCommits = new HashMap<>();
        for (RepoCommit commit : commits) {
            if (userCommits.containsKey(commit.getCommitter().getLogin())) {
                userCommits.replace(commit.getCommitter().getLogin(), userCommits.get(commit.getCommitter().getLogin()) + 1);
            } else {
                userCommits.put(commit.getCommitter().getLogin(), 1);
            }
        }
        model.addAttribute("userCommits", userCommits);
        model.addAttribute("contributors", contributors);
        return "repo-info";
    }
}
