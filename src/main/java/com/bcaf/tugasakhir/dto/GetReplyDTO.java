package com.bcaf.tugasakhir.dto;

import java.util.Date;

public class GetReplyDTO {
    private Long idReply;
    private String comment;
    private UsrDTO user;
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

    public UsrDTO getUser() {
        return user;
    }

    public void setUser(UsrDTO user) {
        this.user = user;
    }

    public Date getTanggalReply() {
        return tanggalReply;
    }

    public void setTanggalReply(Date tanggalReply) {
        this.tanggalReply = tanggalReply;
    }
}
