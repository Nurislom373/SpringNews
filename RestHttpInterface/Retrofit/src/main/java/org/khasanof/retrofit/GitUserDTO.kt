package org.khasanof.retrofit

import com.fasterxml.jackson.annotation.JsonProperty

data class GitUserDTO(

    @JsonProperty("login")
    val login: String,

    @JsonProperty("id")
    val id: Long,

    @JsonProperty("node_id")
    val nodeId: String,

    @JsonProperty("avatar_url")
    val avatarUrl: String,

    @JsonProperty("gravatar_id")
    val gravatarId: String?,

    @JsonProperty("url")
    val url: String,

    @JsonProperty("html_url")
    val htmlUrl: String,

    @JsonProperty("followers_url")
    val followersUrl: String,

    @JsonProperty("following_url")
    val followingUrl: String,

    @JsonProperty("gists_url")
    val gistsUrl: String,

    @JsonProperty("starred_url")
    val starredUrl: String,

    @JsonProperty("subscriptions_url")
    val subscriptionsUrl: String,

    @JsonProperty("organizations_url")
    val organizationsUrl: String,

    @JsonProperty("repos_url")
    val reposUrl: String,

    @JsonProperty("events_url")
    val eventsUrl: String,

    @JsonProperty("received_events_url")
    val receivedEventsUrl: String,

    @JsonProperty("type")
    val type: String,

    @JsonProperty("site_admin")
    val siteAdmin: Boolean,

    @JsonProperty("name")
    val name: String,

    @JsonProperty("company")
    val company: String?,

    @JsonProperty("blog")
    val blog: String?,

    @JsonProperty("location")
    val location: String,

    @JsonProperty("email")
    val email: String?,

    @JsonProperty("hireable")
    val hireable: Boolean?,

    @JsonProperty("bio")
    val bio: String,

    @JsonProperty("twitter_username")
    val twitterUsername: String?,

    @JsonProperty("public_repos")
    val publicRepos: Int,

    @JsonProperty("public_gists")
    val publicGists: Int,

    @JsonProperty("followers")
    val followers: Int,

    @JsonProperty("following")
    val following: Int,

    @JsonProperty("created_at")
    val createdAt: String,

    @JsonProperty("updated_at")
    val updatedAt: String

)
