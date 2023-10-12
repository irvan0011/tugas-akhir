package com.bcaf.tugasakhir.controller;


import com.bcaf.tugasakhir.dto.ReplyDTO;
import com.bcaf.tugasakhir.model.Reply;
import com.bcaf.tugasakhir.service.ReplyService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/reply")
public class ReplyController {

    private ReplyService replyService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public ReplyController(ReplyService replyService){
        this.replyService = replyService;
    }

    @PostMapping("/v1/sv")
    public ResponseEntity<Object> save(@RequestBody ReplyDTO replyDTO, HttpServletRequest request)
    {
        Reply reply = modelMapper.map(replyDTO, new TypeToken<Reply>() {}.getType());
        return replyService.save(reply,request);
    }

    @PutMapping("/v1/upd/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody ReplyDTO replyDTO, HttpServletRequest request)
            throws Exception
    {
        Reply reply = modelMapper.map(replyDTO, new TypeToken<Reply>() {}.getType());
        return replyService.update(id,reply,request);
    }

    @DeleteMapping("/v1/del/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id, HttpServletRequest request)
    {
        return replyService.delete(id,request);
    }

    @GetMapping("/v1/get/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value = "id") Long id, HttpServletRequest request)
    {
        return replyService.findById(id,request);
    }

    @GetMapping("/v1/findall")
    public ResponseEntity<Object> findAll(HttpServletRequest request)
    {
        return replyService.findAll(request);
    }


}
