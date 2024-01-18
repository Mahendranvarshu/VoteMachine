package com.VoteMachine.VoteMachine.Controller;

import com.VoteMachine.VoteMachine.Data.Vote;
import com.VoteMachine.VoteMachine.Service.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VoteController {

    @Autowired
    VotingService votingService;

    @GetMapping("entercandidate")
    public ResponseEntity<String> enterCandidate(@RequestParam String name){

        return votingService.addCandidate(name);
    }


    @GetMapping("castvote")
    public ResponseEntity<String> castVote(@RequestParam String name){

        return votingService.votePole(name);
    }
    @GetMapping("countvote")
    public ResponseEntity<String> countvote(@RequestParam String name){

        return votingService.totalvotes(name);

    }

    @GetMapping("listvote")
    public List<Vote> listvotes(){

        return votingService.listofvotes();

    }
    @GetMapping("getwinner")
    public ResponseEntity<String> getwin(){

        return votingService.getwinner();

    }

}
