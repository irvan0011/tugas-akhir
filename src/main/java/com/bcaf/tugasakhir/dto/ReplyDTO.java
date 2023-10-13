package com.bcaf.tugasakhir.dto;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.Date;

public class ReplyDTO {
    private Long idReply;
    private String comment;
    private UsrDTO user;
    @JsonBackReference
    private PostDTO post;
    private Date tanggalReply = new Date();

    public Long getIdReply() {
        return idReply;
    }

    public void setIdReply(Long idReply) {
        this.idReply = idReply;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public UsrDTO getUser() {
        return user;
    }

    public void setUser(UsrDTO user) {
        this.user = user;
    }

    public PostDTO getPost() {
        return post;
    }

    public void setPost(PostDTO post) {
        this.post = post;
    }

    public Date getTanggalReply() {
        return tanggalReply;
    }

    public void setTanggalReply(Date tanggalReply) {
        this.tanggalReply = tanggalReply;
    }
}
