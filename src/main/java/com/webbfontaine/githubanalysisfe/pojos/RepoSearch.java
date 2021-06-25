package com.webbfontaine.githubanalysisfe.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.webbfontaine.githubanalysisfe.utils.JsonConverter;
import lombok.Data;

import java.util.List;

@Data
public class RepoSearch {
    @JsonProperty("total_count")
    private Long totalCount;

    @JsonProperty("incomplete_results")
    private boolean incompleteResults;

    private List<GithubRepo> items;

    @Override
    public String toString() {
        return JsonConverter.toJson(this);
    }
}
