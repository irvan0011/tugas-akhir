package com.bcaf.tugasakhir.controller;


import com.bcaf.tugasakhir.dto.VoteDTO;
import com.bcaf.tugasakhir.model.Vote;
import com.bcaf.tugasakhir.service.VoteService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/vote")
public class VoteController {

    @Autowired
    private ModelMapper modelMapper;
    private VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping("/v1/sv")
    public ResponseEntity<Object> save(@Valid @RequestBody VoteDTO voteDTO, HttpServletRequest request)
    {
        Vote vote = modelMapper.map(voteDTO, new TypeToken<Vote>() {}.getType());
        return voteService.save(vote,request);
    }

    @PutMapping("/v1/upd/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody VoteDTO voteDTO, HttpServletRequest request)
            throws Exception
    {
        Vote vote = modelMapper.map(voteDTO, new TypeToken<Vote>() {}.getType());
        return voteService.update(id,vote,request);
    }

    @DeleteMapping("/v1/del/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id, HttpServletRequest request)
    {
        return voteService.delete(id,request);
    }

    @GetMapping("/v1/get/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value = "id") Long id, HttpServletRequest request)
    {
        return voteService.findById(id,request);
    }

    @GetMapping("/v1/fbp/{page}/{size}")
    public ResponseEntity<Object> findByPage(
            @PathVariable(value = "page") Integer page,
            @PathVariable(value = "size") Integer size,
            @RequestParam(value = "col") String columFirst,
            @RequestParam(value = "val") String valueFirst,
            HttpServletRequest request)
    {
        return voteService.findByPage(page,size,columFirst,valueFirst,request);
    }

    @GetMapping("/v1/fabp/{page}/{size}")
    public ResponseEntity<Object> findAllByPage(
            @PathVariable(value = "page") Integer page,
            @PathVariable(value = "size") Integer size,
            HttpServletRequest request)
    {
        return voteService.findAllByPage(page,size,request);
    }

    @GetMapping("/v1/findall")
    public ResponseEntity<Object> findAll(HttpServletRequest request)
    {
        return voteService.findAll(request);
    }


}
