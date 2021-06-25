package com.webbfontaine.githubanalysisfe.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.webbfontaine.githubanalysisfe.utils.JsonConverter;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RepoOwner {
    private String login;
    private Long id;
    private String type;
    private String url;
    private String contributions;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    @JsonProperty("site_admin")
    private boolean siteAdmin;

    @Override
    public String toString() {
        return JsonConverter.toJson(this);
    }
}
