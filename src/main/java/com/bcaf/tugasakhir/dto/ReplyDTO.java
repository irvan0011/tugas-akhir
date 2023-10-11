package com.bcaf.tugasakhir.dto;
import java.util.Date;

public class ReplyDTO {
    private Long idReply;
    private String comment;
    private String idUser;
    private PostDTO post;
    private Date tanggalReply;

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

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
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
