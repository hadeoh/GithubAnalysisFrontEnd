package com.webbfontaine.githubanalysisfe.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.webbfontaine.githubanalysisfe.utils.JsonConverter;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RepoCommit {
    private String sha;
    private String url;
    private RepoOwner author;
    private RepoOwner committer;

    @Override
    public String toString() {
        return JsonConverter.toJson(this);
    }
}
