package com.bcaf.tugasakhir.dto;

import com.bcaf.tugasakhir.model.Reply;
import com.bcaf.tugasakhir.model.Usr;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

public class PostDTO {
    private Long idPost;

    private String judulPost;

    private String email;

    private String deskripsi;

    private Integer upvote;

    private String fotoKonten;

    private Date tanggalPost = new Date();

    private LoginDTO user;

    @JsonBackReference
    List<ReplyDTO> listReply;

    public Long getIdPost() {
        return idPost;
    }

    public void setIdPost(Long idPost) {
        this.idPost = idPost;
    }

    public String getJudulPost() {
        return judulPost;
    }

    public void setJudulPost(String judulPost) {
        this.judulPost = judulPost;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public Integer getUpvote() {
        return upvote;
    }

    public void setUpvote(Integer upvote) {
        this.upvote = upvote;
    }

    public String getFotoKonten() {
        return fotoKonten;
    }

    public void setFotoKonten(String fotoKonten) {
        this.fotoKonten = fotoKonten;
    }

    public Date getTanggalPost() {
        return tanggalPost;
    }

    public void setTanggalPost(Date tanggalPost) {
        this.tanggalPost = tanggalPost;
    }

    public LoginDTO getUser() {
        return user;
    }

    public void setUser(LoginDTO user) {
        this.user = user;
    }

    public List<ReplyDTO> getListReply() {
        return listReply;
    }

    public void setListReply(List<ReplyDTO> listReply) {
        this.listReply = listReply;
    }
}
