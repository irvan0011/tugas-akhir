package com.bcaf.tugasakhir.dto;

import com.bcaf.tugasakhir.model.Post;
import com.bcaf.tugasakhir.model.Usr;

import javax.persistence.Column;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class VoteDTO {
    private Long idVote;
    private Boolean isVote = true;
    private PostDTO post;
    private UsrDTO user;

    public Long getIdVote() {
        return idVote;
    }

    public void setIdVote(Long idVote) {
        this.idVote = idVote;
    }

    public Boolean getIsVote() {
        return isVote;
    }

    public void setIsVote(Boolean isVote) {
        this.isVote = isVote;
    }

    public Boolean getVote() {
        return isVote;
    }

    public void setVote(Boolean vote) {
        isVote = vote;
    }

    public PostDTO getPost() {
        return post;
    }

    public void setPost(PostDTO post) {
        this.post = post;
    }

    public UsrDTO getUser() {
        return user;
    }

    public void setUser(UsrDTO user) {
        this.user = user;
    }
}
