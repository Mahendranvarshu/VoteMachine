package com.VoteMachine.VoteMachine.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.IdGeneratorType;

@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String CandidateName;
    private Integer VoteCount ;

    public String getCandidateName() {
        return CandidateName;
    }

    public void setCandidateName(String candidateName) {
        CandidateName = candidateName;
    }

    public Integer getVoteCount() {
        return VoteCount;
    }

    public void setVoteCount(Integer voteCount) {
        VoteCount = voteCount;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "CandidateID='" + id + '\'' +
                "CandidateName='" + CandidateName + '\'' +
                ", VoteCount=" + VoteCount +
                '}';
    }
}
