package com.bcaf.tugasakhir.dto;

import com.bcaf.tugasakhir.model.Post;
import com.bcaf.tugasakhir.model.Usr;

import javax.persistence.Column;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class VoteDTO {
    private Long idVote;
    private Byte isVote;
    private Post post;
    private Usr user;

    public Long getIdVote() {
        return idVote;
    }

    public void setIdVote(Long idVote) {
        this.idVote = idVote;
    }

    public Byte getIsVote() {
        return isVote;
    }

    public void setIsVote(Byte isVote) {
        this.isVote = isVote;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Usr getUser() {
        return user;
    }

    public void setUser(Usr user) {
        this.user = user;
    }
}
