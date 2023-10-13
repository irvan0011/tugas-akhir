package com.bcaf.tugasakhir.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

public class UsrDTO {

    private Long idUser;

private  String name;
private  String jenisKelamin;
    @JsonBackReference
    private List<PostDTO> listPost;

    public UsrDTO() {
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public List<PostDTO> getListPost() {
        return listPost;
    }

    public void setListPost(List<PostDTO> listPost) {
        this.listPost = listPost;
    }
}
