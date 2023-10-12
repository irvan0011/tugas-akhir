package com.bcaf.tugasakhir.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="MstPost")
public class Post implements Serializable {
    private static final Long serializeVersion = 70002L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPost")
    private Long idPost;

    @Column(name="JudulPost")
    private String judulPost;

    @Column(name = "Email")
    private String email;

    @Column(name = "Deskripsi")
    private String deskripsi;

    @Column(name = "Upvote")
    private Integer upvote;

    @Column(name = "FotoKonten")
    private String fotoKonten;

    @Column(name = "TanggalPost",columnDefinition = "DATETIME NOT NULL default GETDATE()")
    private Date tanggalPost = new Date();

    @ManyToOne
    @JoinColumn(name = "IdUser", foreignKey = @ForeignKey(name = "fkPostToUser"))
    private Usr user;

    @OneToMany(mappedBy = "post")
    @JsonBackReference
    private List<Reply> listReply;

    @OneToMany(mappedBy = "post")
    @JsonBackReference
    private List<Vote> listVote;

    public Long getIdPost() {
        return idPost;
    }

    public void setIdPost(Long idPost) {
        this.idPost = idPost;
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

    public String getJudulPost() {
        return judulPost;
    }

    public void setJudulPost(String judulPost) {
        this.judulPost = judulPost;
    }

    public Usr getUser() {
        return user;
    }

    public void setUser(Usr user) {
        this.user = user;
    }

    public List<Reply> getListReply() {
        return listReply;
    }

    public void setListReply(List<Reply> listReply) {
        this.listReply = listReply;
    }

    public List<Vote> getListVote() {
        return listVote;
    }

    public void setListVote(List<Vote> listVote) {
        this.listVote = listVote;
    }
}
