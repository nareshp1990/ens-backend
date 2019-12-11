package com.ens.service.poll;

import com.ens.domain.entity.poll.Poll;
import com.ens.domain.payload.PagedResponse;
import com.ens.domain.payload.poll.PollRequest;
import com.ens.domain.payload.poll.PollResponse;
import com.ens.domain.payload.poll.VoteRequest;
import com.ens.service.IService;
import java.util.UUID;

public interface PollService extends IService<Poll> {

    PagedResponse<PollResponse> getAllPolls(UUID userId,int page, int size);

    Poll createPoll(PollRequest pollRequest, UUID userId);

    PollResponse getPollById(UUID pollId, UUID userId);

    PollResponse castVoteAndGetUpdatedPoll(UUID pollId, VoteRequest voteRequest, UUID userId);

}
