package com.bcaf.tugasakhir.service;

import com.bcaf.tugasakhir.configuration.OtherConfiguration;
import com.bcaf.tugasakhir.core.IService;
import com.bcaf.tugasakhir.dto.PostDTO;
import com.bcaf.tugasakhir.dto.ReplyDTO;
import com.bcaf.tugasakhir.handler.RequestCapture;
import com.bcaf.tugasakhir.handler.ResponseHandler;
import com.bcaf.tugasakhir.model.Post;
import com.bcaf.tugasakhir.repo.LogRequestRepo;
import com.bcaf.tugasakhir.repo.PostRepo;
import com.bcaf.tugasakhir.util.LogTable;
import com.bcaf.tugasakhir.util.LoggingFile;
import com.bcaf.tugasakhir.util.TransformDataPaging;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class PostService implements IService<Post> {

    private PostRepo postRepo;

    private String [] strExceptionArr = new String[2];

    private TransformDataPaging transformDataPaging = new TransformDataPaging();

    private Map<String, Object> mapz = new HashMap<>();


    private LogRequestRepo logRequestRepo;

    private ModelMapper modelMapper;

    @Autowired
    public PostService(PostRepo postRepo, ModelMapper modelMapper) {
        strExceptionArr[0] = "PostService";
        this.postRepo = postRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<Object> save(Post post, HttpServletRequest request) {
        if(post==null)
        {
            return new ResponseHandler().generateResponse(
                    "Data tidak Valid",//message
                    HttpStatus.BAD_REQUEST,//httpstatus
                    null,//object
                    "FV001001",//errorCode Fail Validation modul-code 001 sequence 001 range 001 - 010
                    request
            );
        }
        try{
            postRepo.save(post);
        }catch (Exception e){
            strExceptionArr[1] = "save(KategoriBarang kategoriBarang, HttpServletRequest request) --- LINE 59 \n"+ RequestCapture.allRequest(request);
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfiguration.getFlagLoging());
            LogTable.inputLogRequest(logRequestRepo,strExceptionArr,e,OtherConfiguration.getFlagLogTable());
            return new ResponseHandler().generateResponse(
                    "Data Gagal Disimpan",//message
                    HttpStatus.INTERNAL_SERVER_ERROR,//httpstatus
                    null,//object
                    "FE001001",//errorCode Fail Error modul-code 001 sequence 001 range 001 - 010
                    request
            );
        }
        return new ResponseHandler().generateResponse(
                "Data Berhasil Disimpan",//message
                HttpStatus.CREATED,//httpstatus created
                null,//object
                null,//errorCode diisi null ketika data berhasil disimpan
                request
        );
    }

    @Override
    @Transactional(rollbackFor = {ArithmeticException.class})
    public ResponseEntity<Object> update(Long id, Post post, HttpServletRequest request) {
        Optional<Post> postOptional;
        Post postModel;

        Boolean isValid = true;
        try{
            postOptional =  postRepo.findById(id);

            if(postOptional.isEmpty())
            {
                return new ResponseHandler().generateResponse(
                        "Data tidak Valid",//message
                        HttpStatus.BAD_REQUEST,//httpstatus
                        null,//object
                        "FV001011",//errorCode Fail Validation modul-code 001 sequence 001 range 011 - 020
                        request
                );
            }

            postModel = postOptional.get();
            postModel.setJudulPost(post.getJudulPost());
        }
        catch (Exception e)
        {
            isValid = false;
            strExceptionArr[1] = " update(Long id,KategoriBarang kategoriBarang, HttpServletRequest request) --- LINE 101 \n"+RequestCapture.allRequest(request);
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfiguration.getFlagLoging());
            LogTable.inputLogRequest(logRequestRepo,strExceptionArr,e,OtherConfiguration.getFlagLogTable());
            return new ResponseHandler().generateResponse(
                    "Data tidak Valid",//message
                    HttpStatus.BAD_REQUEST,//httpstatus
                    null,//object
                    "FV001011",//errorCode Fail Validation modul-code 001 sequence 001 range 011 - 020
                    request
            );
        }

        return new ResponseHandler().generateResponse(
                "Data Berhasil Diubah",//message
                HttpStatus.CREATED,//httpstatus seharusnya no content 204 (permintaan berhasil tapi tidak ada content untuk dikirim dalam response)
                null,//object
                null,//errorCode diisi null ketika data berhasil disimpan
                request
        );
    }

    @Override
    public ResponseEntity<Object> delete(Long id, HttpServletRequest request) {
        Optional<Post> postOptional = postRepo.findById(id);

        if(postOptional.isEmpty()){
            return new ResponseHandler().generateResponse(
                    "Data tidak Valid",//message
                    HttpStatus.BAD_REQUEST,//httpstatus
                    null,//object
                    "FV001021",//errorCode Fail Validation modul-code 001 sequence 001 range 021 - 030
                    request
            );
        }

        try{
            postRepo.deleteById(id);
        }catch (Exception e){
            strExceptionArr[1] = "delete(Long id, HttpServletRequest request) --- LINE 164 \n"+RequestCapture.allRequest(request);
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfiguration.getFlagLoging());
            LogTable.inputLogRequest(logRequestRepo,strExceptionArr,e,OtherConfiguration.getFlagLogTable());
            return new ResponseHandler().generateResponse(
                    "Data Gagal Dihapus",//message
                    HttpStatus.INTERNAL_SERVER_ERROR,//httpstatus
                    null,//object
                    "FE001021",//errorCode Fail Error modul-code 001 sequence 001 range 021 - 030
                    request
            );
        }
        return new ResponseHandler().generateResponse(
                "Data Berhasil Dihapus",//message
                HttpStatus.CREATED,//httpstatus seharusnya no content 204 (permintaan berhasil tapi tidak ada content untuk dikirim dalam response)
                null,//object
                null,//errorCode diisi null ketika data berhasil disimpan
                request
        );
    }

    @Override
    public ResponseEntity<Object> saveBatch(List<Post> lt, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> findById(Long id, HttpServletRequest request) {
        Optional<Post> postOptional;
        PostDTO postDTO;
        try{
            postOptional = postRepo.findById(id);

            if(postOptional == null){
                return new ResponseHandler().generateResponse(
                        "Data tidak Ditemukan",//message
                        HttpStatus.NOT_FOUND,//httpstatus
                        null,//object
                        "FV001041",//errorCode Fail Validation modul-code 001 sequence 001 range 041 - 050
                        request
                );
            }
            postDTO = modelMapper.map(postOptional,new TypeToken<PostDTO>() {}.getType());
        }catch (Exception e){
            strExceptionArr[1] = "findById(Long id, HttpServletRequest request) --- LINE 249 \n"+RequestCapture.allRequest(request);
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfiguration.getFlagLoging());
            LogTable.inputLogRequest(logRequestRepo,strExceptionArr,e,OtherConfiguration.getFlagLogTable());
            return new ResponseHandler().generateResponse(
                    "Data tidak Valid",//message
                    HttpStatus.INTERNAL_SERVER_ERROR,//httpstatus
                    null,//object
                    "FE001041",//errorCode Fail Validation modul-code 001 sequence 001 range 041 - 050
                    request
            );
        }
        return new ResponseHandler().generateResponse(
                "Data Ditemukan",//message
                HttpStatus.OK,//httpstatus OK
                postDTO,//object
                null,//errorCode diisi null ketika data berhasil disimpan
                request
        );
    }

    @Override
    public ResponseEntity<Object> findByPage(Integer page, Integer size, String columFirst, String valueFirst, HttpServletRequest request) {
        Page<Post> pagePost;
        List<PostDTO> listPostDTO;

        try{
            Pageable pageable = PageRequest.of(page,size, Sort.by("idPost"));
            if(columFirst.equals("judulPost"))
            {
                pagePost = postRepo.findByJudulPostContains(pageable,valueFirst);
            } else {
                pagePost = postRepo.findAll(pageable);
            }
            listPostDTO = modelMapper.map(pagePost.getContent(), new TypeToken<List<PostDTO>>() {}.getType());
            int dataSize = pagePost.getContent().size();

            if(dataSize==0)
            {
                return new ResponseHandler().generateResponse(
                        "Data tidak Ditemukan",//message
                        HttpStatus.NOT_FOUND,//httpstatus
                        null,//object
                        "FV001051",//errorCode Fail Validation modul-code 001 sequence 001 range 051 - 060
                        request
                );
            }
        }catch (Exception e)
        {
            strExceptionArr[1] = "findByPage(Integer page, Integer size,String columFirst, String valueFirst, HttpServletRequest request) --- LINE 304 \n"+RequestCapture.allRequest(request);
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfiguration.getFlagLoging());
            LogTable.inputLogRequest(logRequestRepo,strExceptionArr,e,OtherConfiguration.getFlagLogTable());
            return new ResponseHandler().generateResponse(
                    "Data tidak Valid",//message
                    HttpStatus.INTERNAL_SERVER_ERROR,//httpstatus
                    null,//object
                    "FE001051",//errorCode Fail Validation modul-code 001 sequence 001 range 051 - 060
                    request
            );
        }
        return new ResponseHandler().generateResponse(
                "Data Ditemukan",//message
                HttpStatus.OK,//httpstatus OK
                transformDataPaging.mapDataPaging(mapz,pagePost,listPostDTO),//object
                null,//errorCode diisi null ketika data berhasil disimpan
                request
        );
    }

    @Override
    public ResponseEntity<Object> findAllByPage(Integer page, Integer size, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> findAll(HttpServletRequest request) {
        List<Post> listPost;

        List<PostDTO> listpostDTO;
        try{
            listPost = postRepo.findAll();

            listpostDTO = modelMapper.map(listPost, new TypeToken<List<PostDTO>>() {}.getType());
            if(listPost.size()==0)
            {
                return new ResponseHandler().generateResponse(
                        "Data tidak Ditemukan",//message
                        HttpStatus.NOT_FOUND,//httpstatus
                        null,//object
                        "FV001071",//errorCode Fail Validation modul-code 001 sequence 001 range 071 - 080
                        request
                );
            }
        }catch (Exception e)
        {
            strExceptionArr[1] = "findAll(HttpServletRequest request) --- LINE 382 \n"+RequestCapture.allRequest(request);
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfiguration.getFlagLoging());
            return new ResponseHandler().generateResponse(
                    "Data tidak Valid",//message
                    HttpStatus.INTERNAL_SERVER_ERROR,//httpstatus
                    null,//object
                    "FE001071",//errorCode Fail Validation modul-code 001 sequence 001 range 071 - 080
                    request
            );
        }

        return new ResponseHandler().generateResponse(
                "Data Ditemukan",//message
                HttpStatus.OK,//httpstatus OK
                listpostDTO,//object
                null,//errorCode diisi null ketika data berhasil disimpan
                request
        );
    }

    @Override
    public ResponseEntity<Object> dataToExport(MultipartFile multipartFile, HttpServletRequest request) {
        return null;
    }
}
