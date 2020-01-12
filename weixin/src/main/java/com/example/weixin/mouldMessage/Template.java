package com.example.weixin.mouldMessage;

import com.alibaba.fastjson.annotation.JSONType;

/**
 * {
 *            "touser":"OPENID",
 *            "template_id":"ngqIpbwh8bUfcSsECmogfXcV14J0tQlEpBO27izEYtY",
 *            "url":"http://weixin.qq.com/download",
 *            "data":{
 *                    "first": {
 *                        "value":"恭喜你购买成功！",
 *                        "color":"#173177"
 *                    },
 *                    "keyword1":{
 *                        "value":"巧克力",
 *                        "color":"#173177"
 *                    },
 *                    "keyword2": {
 *                        "value":"39.8元",
 *                        "color":"#173177"
 *                    },
 *                    "keyword3": {
 *                        "value":"2014年9月22日",
 *                        "color":"#173177"
 *                    },
 *                    "remark":{
 *                        "value":"欢迎再次购买！",
 *                        "color":"#173177"
 *                    }
 *            }
 *        }
 */
@JSONType(orders = {"touser","template_id","url","data"})
public class Template {
    private String touser;
    private String template_id;
    private String url;
    private Data data;

    public Template(String touser, String template_id, String url, Data data) {
        this.touser = touser;
        this.template_id = template_id;
        this.url = url;
        this.data = data;
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
