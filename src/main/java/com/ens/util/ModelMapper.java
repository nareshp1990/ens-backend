package com.ens.util;

import com.ens.domain.entity.poll.Poll;
import com.ens.domain.entity.user.User;
import com.ens.domain.payload.poll.ChoiceResponse;
import com.ens.domain.payload.poll.PollResponse;
import com.ens.domain.payload.poll.UserSummary;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ModelMapper {

    public static PollResponse mapPollToPollResponse(Poll poll, Map<Long, Long> choiceVotesMap, User creator, Long userVote) {
        PollResponse pollResponse = new PollResponse();
        pollResponse.setId(poll.getId());
        pollResponse.setQuestion(poll.getQuestion());
        pollResponse.setCreationDateTime(poll.getCreatedAt());
        pollResponse.setExpirationDateTime(poll.getExpirationDateTime());
        LocalDateTime now = LocalDateTime.now();
        pollResponse.setIsExpired(poll.getExpirationDateTime().isBefore(now));

        List<Long> choiceIds = poll.getChoices().stream().map(e -> e.getId())
                .collect(Collectors.toList());

        List<ChoiceResponse> choiceResponses = poll.getChoices().stream().map(choice -> {
            ChoiceResponse choiceResponse = new ChoiceResponse();
            choiceResponse.setId(choice.getId());
            choiceResponse.setText(choice.getText());

            if(choiceVotesMap.containsKey(choice.getId())) {

                choiceResponse.setVoteCount(choiceVotesMap.get(choice.getId()));

//                long totalVotesCountSum = choiceVotesMap.values().stream().mapToLong(e -> e.longValue()).sum();
                long totalVotesCountSum = choiceVotesMap.entrySet().stream()
                    .filter(e -> choiceIds.contains(e.getKey()))
                    .mapToLong(Map.Entry::getValue).sum();

                double pollPercentage = (choiceResponse.getVoteCount() * 100.0) / totalVotesCountSum;

                choiceResponse.setPollPercentage(pollPercentage);

            } else {
                choiceResponse.setVoteCount(0);
            }
            return choiceResponse;
        }).collect(Collectors.toList());

        pollResponse.setChoices(choiceResponses);
        UserSummary creatorSummary = new UserSummary(creator.getId(), creator.getUserName(), creator.getEmail(), creator.getProfileImageUrl());
        pollResponse.setCreatedBy(creatorSummary);

        if(userVote != null) {
            pollResponse.setSelectedChoice(userVote);
        }

        long totalVotes = pollResponse.getChoices().stream().mapToLong(ChoiceResponse::getVoteCount).sum();
        pollResponse.setTotalVotes(totalVotes);

        return pollResponse;
    }

}
