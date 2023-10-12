package com.bcaf.tugasakhir.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Vote")
public class Vote implements Serializable {
    private static final Long serializeVersion = 70002L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdVote")
    private Long idVote;

    @Column(name= "IsVote")
    private Boolean isVote;

    @ManyToOne
    @JoinColumn(name = "IdPost", foreignKey = @ForeignKey(name = "fkVotetoPost"))
    private Post post;

    @ManyToOne
    @JoinColumn(name = "IdUser", foreignKey = @ForeignKey(name = "fkVotetoUser"))
    private Usr user;

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
