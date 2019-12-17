package com.ens.service.poll;

import com.ens.domain.entity.poll.Poll;
import com.ens.domain.payload.PagedResponse;
import com.ens.domain.payload.poll.PollRequest;
import com.ens.domain.payload.poll.PollResponse;
import com.ens.domain.payload.poll.VoteRequest;
import com.ens.service.IService;

public interface PollService extends IService<Poll> {

    PagedResponse<PollResponse> getAllPolls(Long userId,int page, int size);

    Poll createPoll(PollRequest pollRequest, Long userId);

    PollResponse getPollById(Long pollId, Long userId);

    PollResponse castVoteAndGetUpdatedPoll(Long pollId, VoteRequest voteRequest, Long userId);

}
