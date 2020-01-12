package com.example.weixin.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;
import java.util.Map;

/**
 * <xml>
 *   <ToUserName><![CDATA[toUser]]></ToUserName>
 *   <FromUserName><![CDATA[fromUser]]></FromUserName>
 *   <CreateTime>12345678</CreateTime>
 *   <MsgType><![CDATA[news]]></MsgType>
 *   <ArticleCount>1</ArticleCount>
 *   <Articles>
 *     <item>
 *       <Title><![CDATA[title1]]></Title>
 *       <Description><![CDATA[description1]]></Description>
 *       <PicUrl><![CDATA[picurl]]></PicUrl>
 *       <Url><![CDATA[url]]></Url>
 *     </item>
 *   </Articles>
 * </xml>
 */
@XStreamAlias("xml")
public class NewsMessage extends BaseMessage{

    @XStreamAlias("ArticleCount")
    private String articleCount;

    @XStreamAlias("Articles")
    private List<Article> articles;


    public String getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(String articleCount) {
        this.articleCount = articleCount;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public NewsMessage(Map<String, String> reqmap, List<Article> articles) {

        super(reqmap);
        this.articleCount = articles.size() + "";
        this.articles = articles;
        this.setMsgType("news");
    }
}
