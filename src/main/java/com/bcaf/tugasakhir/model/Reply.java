package com.bcaf.tugasakhir.model;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Reply")
public class Reply implements Serializable{
    private static final Long serializeVersion = 70002L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdReply")
    private Long idReply;

    @Column(name = "Comment")
    private String comment;

    @Column(name = "IdUser")
    private String idUser;

    @ManyToOne
    @JoinColumn(name = "IdPostz", foreignKey = @ForeignKey(name = "fkReplytoPost"))
    private Post post;

    @Column(name = "TanggalReply",columnDefinition = "DATETIME NOT NULL default GETDATE()")
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

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Date getTanggalReply() {
        return tanggalReply;
    }

    public void setTanggalReply(Date tanggalReply) {
        this.tanggalReply = tanggalReply;
    }
}
