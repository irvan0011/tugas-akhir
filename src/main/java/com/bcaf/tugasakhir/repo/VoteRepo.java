package com.bcaf.tugasakhir.repo;

import com.bcaf.tugasakhir.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepo extends JpaRepository<Vote,Long> {

}
