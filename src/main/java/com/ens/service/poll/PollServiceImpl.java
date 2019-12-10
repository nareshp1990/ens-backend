package com.ens.service.poll;

import com.ens.domain.entity.poll.Poll;
import com.ens.repo.poll.PollRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PollServiceImpl implements PollService {

    @Autowired
    private PollRepository repository;

    @Override
    public Poll save(Poll poll) {
        return repository.save(poll);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Poll> findOne(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<Poll> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Poll> findAll(Pageable pgbl) {
        return repository.findAll(pgbl);
    }
}
