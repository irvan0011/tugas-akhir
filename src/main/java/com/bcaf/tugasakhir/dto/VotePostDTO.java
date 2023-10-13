package com.bcaf.tugasakhir.dto;

import java.util.Date;
import java.util.List;

public class VotePostDTO {

    private Long idPost;

    private String judulPost;

    private String email;

    private String deskripsi;

    private Integer upvote;

    private String fotoKonten;

    private Date tanggalPost = new Date();

    private UsrDTO user;

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

    public UsrDTO getUser() {
        return user;
    }

    public void setUser(UsrDTO user) {
        this.user = user;
    }
}
