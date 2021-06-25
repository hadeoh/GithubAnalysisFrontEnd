package com.webbfontaine.githubanalysisfe.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.webbfontaine.githubanalysisfe.utils.JsonConverter;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubRepo {
    private Long id;
    @JsonProperty("node_id")
    private String nodeId;
    private String name;
    @JsonProperty("full_name")
    private String fullName;
    private RepoOwner owner;
    @JsonProperty("private")
    private boolean isPrivate;
    @JsonProperty("html_url")
    private String htmlUrl;
    private String description;
    private boolean fork;
    private String url;
    @JsonProperty("archive_url")
    private String archiveUrl;
    @JsonProperty("assignees_url")
    private String assignees_url;
    @JsonProperty("collaborators_url")
    private String collaborators_url;
    @JsonProperty("commits_url")
    private String commitsUrl;
    @JsonProperty("git_commits_url")
    private String gitCommitsUrl;

    @Override
    public String toString() {
        return JsonConverter.toJson(this);
    }
}
