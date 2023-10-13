package com.bcaf.tugasakhir.repo;

import com.bcaf.tugasakhir.model.Post;
import com.bcaf.tugasakhir.model.Usr;
import com.bcaf.tugasakhir.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VoteRepo extends JpaRepository<Vote,Long> {

    public Optional<Vote> findByPostAndUser(Post idpost, Usr iduser);
    public List<Vote> findByPostAndIsVote(Post idpost,Boolean isvote);
}
