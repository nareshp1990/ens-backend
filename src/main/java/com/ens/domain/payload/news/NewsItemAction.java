package com.ens.domain.payload.news;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsItemAction implements Serializable {

    private static final long serialVersionUID = 1L;

    protected long views;
    protected long likes;
    protected long unLikes;
    protected long comments;
    protected long whatsAppShares;
    protected long facebookShares;
    protected long instagramShares;
    protected long helloAppShares;
    protected long twitterShares;
    protected long telegramShares;
}
