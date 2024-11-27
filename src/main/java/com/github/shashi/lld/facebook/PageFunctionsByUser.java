package com.github.shashi.lld.facebook;

public interface PageFunctionsByUser {
    public Page createPage(String name);
    public Page sharePage(Page page);
    public void likePage(Page page);
    public void followPage(Page page);
    public void unLikePage(Page page);
    public void unFollowPage(Page page);
}