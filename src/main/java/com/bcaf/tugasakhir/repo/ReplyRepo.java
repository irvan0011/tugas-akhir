package com.bcaf.tugasakhir.repo;

import com.bcaf.tugasakhir.model.Post;
import com.bcaf.tugasakhir.model.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepo extends JpaRepository<Reply, Long> {
}
