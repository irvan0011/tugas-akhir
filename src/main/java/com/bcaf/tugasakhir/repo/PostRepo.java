package com.bcaf.tugasakhir.repo;

import com.bcaf.tugasakhir.model.Post;
import com.bcaf.tugasakhir.model.Usr;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepo extends JpaRepository<Post, Long> {
    public Page<Post> findByJudulPostContains(Pageable p, String val);
    public Page<Post> findByOrderByUpvoteDesc(Pageable p);
    public Page<Post> findByOrderByUpvoteAsc(Pageable p);
    public Page<Post> findByOrderByTanggalPostDesc(Pageable p);
    public Page<Post> findByOrderByTanggalPostAsc(Pageable p);
    public Page<Post> findByUser(Pageable p, Usr val);
}
