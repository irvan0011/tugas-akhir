package com.bcaf.tugasakhir.controller;


import com.bcaf.tugasakhir.dto.LoginDTO;
import com.bcaf.tugasakhir.dto.LoginTokenDTO;
import com.bcaf.tugasakhir.dto.RegisterDTO;
import com.bcaf.tugasakhir.model.Usr;
import com.bcaf.tugasakhir.service.UsrService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/op")
public class OpenController {
    private UsrService usrService;
    private ModelMapper modelMapper;
    /*
        Wajib di class controller agar tidak terjadi cycle
     */
//    @Autowired
//    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public OpenController(UsrService usrService, ModelMapper modelMapper, AuthenticationManager authenticationManager, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usrService = usrService;
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @PostMapping("/v1/gt")
        public ResponseEntity<Object> getToken(@RequestBody LoginDTO loginDTO, HttpServletRequest request) throws Exception{

        return usrService.authManager(loginDTO,request);
    }
    @PostMapping("/v1/regis")
    public ResponseEntity<Object> regis(@RequestBody RegisterDTO registerDTO, HttpServletRequest request)
    {
        Usr usr = modelMapper.map(registerDTO, new TypeToken<Usr>() {}.getType());;
        return usrService.registrationUser(usr,request);
    }


//    @GetMapping("/v1/checkToken")
//    public void testDoank(@RequestParam(value = "token") String strToken,@RequestParam(value = "tokenHash") String strHashToken)
//    {
//        System.out.println("Token is "+bCryptPasswordEncoder.matches(strToken,strHashToken));
//    }
}
