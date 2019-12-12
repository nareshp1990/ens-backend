package com.ens.domain.entity.news;

import com.ens.domain.payload.news.NewsItemAction;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsItemActionResponse extends NewsItemAction {

    private static final long serialVersionUID = 1L;

    private UUID newsItemId;

    public NewsItemActionResponse(long views, long likes, long unLikes, long comments,
            long whatsAppShares, long facebookShares, long instagramShares, long helloAppShares,
            long twitterShares, long telegramShares, UUID newsItemId) {
        super(views, likes, unLikes, comments, whatsAppShares, facebookShares, instagramShares,
                helloAppShares, twitterShares, telegramShares);
        this.newsItemId = newsItemId;
    }
}
