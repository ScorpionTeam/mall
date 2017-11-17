package com.scoprion.mall.domain;

/**
 * 反馈
 *
 * @author by kunlun
 * @created on 2017/11/16.
 */
public class RetroAction {

    /**
     * 主键
     */
    private Long id;

    /**
     * 反馈类型
     */
    private String retroActionType;

    /**
     * 反馈内容
     */
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRetroActionType() {
        return retroActionType;
    }

    public void setRetroActionType(String retroActionType) {
        this.retroActionType = retroActionType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "RetroAction{" +
                "id=" + id +
                ", retroActionType='" + retroActionType + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
