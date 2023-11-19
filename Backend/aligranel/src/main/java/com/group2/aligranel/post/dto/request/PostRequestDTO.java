package com.group2.aligranel.post.dto.request;

import org.bson.types.ObjectId;

import java.util.List;

public class PostRequestDTO {
    private String content;
    private String mediaUrl;
    private String productId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
