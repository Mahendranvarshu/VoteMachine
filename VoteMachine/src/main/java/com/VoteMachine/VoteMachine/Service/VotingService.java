package com.VoteMachine.VoteMachine.Service;

import com.VoteMachine.VoteMachine.Data.Vote;
import com.VoteMachine.VoteMachine.Repo.VoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class VotingService {


    @Autowired
    VoteRepo voteRepo;
    public ResponseEntity<String> addCandidate(String candidateName) {

        List<Vote> votes = new ArrayList<>();
        votes=voteRepo.findAll();
        for (Vote v : votes) {
            if (v.getCandidateName().equals(candidateName)) {
                return new ResponseEntity<>("Candidate with the given name already exists", HttpStatus.BAD_REQUEST);
            }
        }


        Vote vote = new Vote();
        vote.setCandidateName(candidateName);
        vote.setVoteCount(0);
        voteRepo.save(vote);
        return new ResponseEntity<>("Nomination is Success", HttpStatus.CREATED);

    }

    public ResponseEntity<String> votePole(String name) {

        List<Vote> votes = new ArrayList<>();
        votes=voteRepo.findAll();
        for (Vote v : votes) {
            if (v.getCandidateName().equals(name)) {
                v.setVoteCount(v.getVoteCount()+1);
                voteRepo.saveAndFlush(v);
                return new ResponseEntity<>("Pole is Success", HttpStatus.OK);
                }

        }
        return new ResponseEntity<>("Candiate name is wrong", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> totalvotes(String name) {
        List<Vote> votes = new ArrayList<>();
        votes=voteRepo.findAll();
        for (Vote v : votes) {
            if (v.getCandidateName().equals(name)) {

                return new ResponseEntity<>("The total Votes of = "+ v.getVoteCount().toString(), HttpStatus.OK);
            }

        }
        return new ResponseEntity<>("Candiate name is wrong", HttpStatus.BAD_REQUEST);

    }

    public List<Vote> listofvotes() {
        List<Vote> votes = voteRepo.findAll();

        votes.sort(Comparator.comparingInt(Vote::getVoteCount).reversed());
        return votes;

    }

    public ResponseEntity<String> getwinner() {
        List<Vote> votes = voteRepo.findAll();
        if (votes.isEmpty()) {
            return new ResponseEntity<>("No votes found", HttpStatus.NOT_FOUND);
        }

        // Sort the votes based on vote count in descending order
        votes.sort(Comparator.comparingInt(Vote::getVoteCount).reversed());
        Vote winner = votes.get(0);
        return new ResponseEntity<>("The Winner is : " + winner.getCandidateName() + "\n"+" The Total number of Vote is : "+winner.getVoteCount() , HttpStatus.OK);

    }
}
