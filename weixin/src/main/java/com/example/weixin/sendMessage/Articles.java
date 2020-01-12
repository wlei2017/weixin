package com.example.weixin.sendMessage;


import java.util.List;

public class Articles {
    private List<Article> articles;

    public Articles(List<Article> articles) {
        this.articles = articles;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
