package com.zhy.project.mall.model.vo;

import java.util.Date;

public class GoodsMsgVO {
    private Integer id;
    private String content;
    private String asker;
    private Date time;
    private replyVO reply = new replyVO();

    public void setReplyContent(String replyContent){
        reply.setContent(replyContent);
    }

    public void setReplytime(Date date){
        reply.setTime(date);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAsker() {
        return asker;
    }

    public void setAsker(String asker) {
        this.asker = asker;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public replyVO getReply() {
        return reply;
    }

    public void setReply(replyVO reply) {
        this.reply = reply;
    }
}
