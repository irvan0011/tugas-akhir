package com.bcaf.tugasakhir.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "MstUser")
public class Usr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdUser")
    private Long idUser;

    @Column(name = "UserName",unique = true)
    private String userName;
    @Column(name = "Email",unique = true)
    private String email;

    @Column(name = "Password")
    private String password;

    @Column(name = "Name")
    private String name;

    @Column(name = "FotoUser")
    private String fotoUser;

    @Column(name = "JenisKelamin")
    private String jenisKelamin;

    @Column(name = "Token")
    private String token;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Vote> listVote;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Post> listPost;

    public List<Vote> getListVote() {
        return listVote;
    }

    public void setListVote(List<Vote> listVote) {
        this.listVote = listVote;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getFotoUser() {
        return fotoUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFotoUser(String fotoUser) {
        this.fotoUser = fotoUser;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Post> getListPost() {
        return listPost;
    }

    public void setListPost(List<Post> listPost) {
        this.listPost = listPost;
    }
}