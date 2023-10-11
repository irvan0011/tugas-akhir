package com.bcaf.tugasakhir.controller;


import com.bcaf.tugasakhir.dto.PostDTO;
import com.bcaf.tugasakhir.model.Post;
import com.bcaf.tugasakhir.service.PostService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private ModelMapper modelMapper;
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/v1/sv")
    public ResponseEntity<Object> save(@RequestBody PostDTO postDTO, HttpServletRequest request)
    {
        Post post = modelMapper.map(postDTO, new TypeToken<Post>() {}.getType());
        return postService.save(post,request);
    }

    @PutMapping("/v1/upd/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody PostDTO postDTO, HttpServletRequest request)
            throws Exception
    {
        Post post = modelMapper.map(postDTO, new TypeToken<Post>() {}.getType());
        return postService.update(id,post,request);
    }

    @DeleteMapping("/v1/del/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id, HttpServletRequest request)
    {
        return postService.delete(id,request);
    }

    @GetMapping("/v1/get/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value = "id") Long id, HttpServletRequest request)
    {
        return postService.findById(id,request);
    }

    @GetMapping("/v1/fbp/{page}/{size}")
    public ResponseEntity<Object> findByPage(
            @PathVariable(value = "page") Integer page,
            @PathVariable(value = "size") Integer size,
            @RequestParam(value = "col") String columFirst,
            @RequestParam(value = "val") String valueFirst,
            HttpServletRequest request)
    {
        return postService.findByPage(page,size,columFirst,valueFirst,request);
    }

    @GetMapping("/v1/findall")
    public ResponseEntity<Object> findAll(HttpServletRequest request)
    {
        return postService.findAll(request);
    }

//    private
}
