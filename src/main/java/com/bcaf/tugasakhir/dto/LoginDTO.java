package com.bcaf.tugasakhir.dto;

import com.bcaf.tugasakhir.model.Post;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

public class LoginDTO {

    private Long idUser;

    @NotNull
    @NotEmpty
    @NotBlank
//    @Pattern(regexp = "^\\w{8,15}$",message = "Format user tidak boleh spasi (minimal 8 maksimal 15)")
//    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,15}$",message = "Format user tidak boleh spasi (minimal 8 maksimal 15)")
    private String userName;

    @NotNull
    @NotEmpty
    @NotBlank
    //    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[*.!@$%^&(){}:;<>,.?/~_+-=|]).{10,20}$",message = "Kombinasi Huruf,Angka dan spesial character !!")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{10,20}$",message = "Minimal 1 simbol & alfanumeric , ex : Paul@123... Kombinasi Huruf dan Angka !!(Password Minimal 10 Maksimal 20 Karakter)")
//    @Pattern(regexp = "",message = "Minimal 1 simbol & alfanumeric , ex : Paul@123...")//MINIMAL 1 SIMBOL & ALFANUMERIK
    private String password;

    @JsonBackReference
    private List<PostDTO> listPost;

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PostDTO> getListPost() {
        return listPost;
    }

    public void setListPost(List<PostDTO> listPost) {
        this.listPost = listPost;
    }
}
