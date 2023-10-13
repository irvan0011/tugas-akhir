package com.bcaf.tugasakhir.repo;

import com.bcaf.tugasakhir.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepo extends JpaRepository<Post, Long> {
    public Page<Post> findByJudulPostContains(Pageable p, String val);
}
