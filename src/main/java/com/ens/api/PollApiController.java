package com.ens.api;

import com.ens.domain.entity.poll.Poll;
import com.ens.domain.payload.ApiResponse;
import com.ens.domain.payload.PagedResponse;
import com.ens.domain.payload.poll.PollRequest;
import com.ens.domain.payload.poll.PollResponse;
import com.ens.domain.payload.poll.VoteRequest;
import com.ens.service.poll.PollService;
import com.ens.util.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import java.net.URI;
import java.util.UUID;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Api(value = "poll service", description = "The poll service API", tags = {"poll"})
@RestController
@Slf4j
@RequestMapping("/v1/api/polls")
public class PollApiController {

    @Autowired
    private PollService pollService;

    @ApiOperation(value = "get all polls", tags = {"poll"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "poll response", response = PollResponse.class)})
    @GetMapping(value = "/{userId}")
    public PagedResponse<PollResponse> getPolls(@PathVariable UUID userId,
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return pollService.getAllPolls(userId, page, size);
    }

    @ApiOperation(value = "create poll", tags = {"poll"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("/{userId}")
    public ResponseEntity<?> createPoll(@PathVariable UUID userId, @Valid @RequestBody PollRequest pollRequest) {

        Poll poll = pollService.createPoll(pollRequest,userId);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{userId}/{pollId}")
                .buildAndExpand(userId,poll.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Poll Created Successfully"));
    }

    @ApiOperation(value = "get poll by id", tags = {"poll"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "poll response", response = PollResponse.class)})
    @GetMapping("/{userId}/{pollId}")
    public PollResponse getPollById(@PathVariable UUID userId, @PathVariable UUID pollId) {
        return pollService.getPollById(pollId, userId);
    }

    @ApiOperation(value = "cast vote", tags = {"poll"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("/{userId}/{pollId}/votes")
    public PollResponse castVote(@PathVariable UUID userId, @PathVariable UUID pollId,
            @Valid @RequestBody VoteRequest voteRequest) {
        return pollService.castVoteAndGetUpdatedPoll(pollId, voteRequest, userId);
    }

    @ApiOperation(value = "delete poll", tags = {"poll"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @DeleteMapping("/{pollId}")
    public ResponseEntity<ApiResponse> deletePoll(@PathVariable UUID pollId){
        pollService.delete(pollId);
        return ResponseEntity.ok(new ApiResponse(true, "Poll Deleted Successfully"));
    }

}
