package com.idea.model;

import java.util.Date;

public class IdeaInfo {

    private long id;

    private String name;

    private String fullName;

    private String brief;

    private String intro;

    private String androidLink;

    private String iphoneAppstoreLink;

    private String logo;

    private String story;

    private String webLink;

    private String website;

    private String weibo;

    private String weixin;

    private String founderName;

    private Date startDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getAndroidLink() {
        return androidLink;
    }

    public void setAndroidLink(String androidLink) {
        this.androidLink = androidLink;
    }

    public String getIphoneAppstoreLink() {
        return iphoneAppstoreLink;
    }

    public void setIphoneAppstoreLink(String iphoneAppstoreLink) {
        this.iphoneAppstoreLink = iphoneAppstoreLink;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getWebLink() {
        return webLink;
    }

    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getWeibo() {
        return weibo;
    }

    public void setWeibo(String weibo) {
        this.weibo = weibo;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getFounderName() {
        return founderName;
    }

    public void setFounderName(String founderName) {
        this.founderName = founderName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "IdeaInfo [id=" + id + ", name=" + name + ", fullName=" + fullName + ", brief=" + brief + ", intro="
                + intro + ", androidLink=" + androidLink + ", iphoneAppstoreLink=" + iphoneAppstoreLink + ", logo="
                + logo + ", story=" + story + ", webLink=" + webLink + ", website=" + website + ", weibo=" + weibo
                + ", weixin=" + weixin + ", founderName=" + founderName + ", startDate=" + startDate + "]";
    }

}
