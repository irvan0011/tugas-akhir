package com.bcaf.tugasakhir.service;

import com.bcaf.tugasakhir.configuration.OtherConfiguration;
import com.bcaf.tugasakhir.core.IService;
import com.bcaf.tugasakhir.dto.PostDTO;
import com.bcaf.tugasakhir.dto.VoteDTO;
import com.bcaf.tugasakhir.dto.VotePostDTO;
import com.bcaf.tugasakhir.handler.RequestCapture;
import com.bcaf.tugasakhir.handler.ResponseHandler;
import com.bcaf.tugasakhir.model.Post;
import com.bcaf.tugasakhir.model.Usr;
import com.bcaf.tugasakhir.model.Vote;
import com.bcaf.tugasakhir.repo.LogRequestRepo;
import com.bcaf.tugasakhir.repo.PostRepo;
import com.bcaf.tugasakhir.repo.VoteRepo;
import com.bcaf.tugasakhir.util.LogTable;
import com.bcaf.tugasakhir.util.LoggingFile;
import com.bcaf.tugasakhir.util.TransformDataPaging;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class VoteService implements IService<Vote> {

    private VoteRepo voteRepo;
    private PostRepo postRepo;

    private String [] strExceptionArr = new String[2];

    private TransformDataPaging transformDataPaging = new TransformDataPaging();
    private Map<String,Object> mapz = new HashMap<>();
    @Autowired
    private LogRequestRepo logRequestRepo;

    @Autowired
    private LogService logService;
    private ModelMapper modelMapper;


    @Autowired
    public VoteService(VoteRepo voteRepo, ModelMapper modelMapper,PostRepo postRepo) {
        strExceptionArr[0] = "VoteService";
        this.voteRepo = voteRepo;
        this.modelMapper = modelMapper;
        this.postRepo = postRepo;
    }

    @Override
    public ResponseEntity<Object> save(Vote vote, HttpServletRequest request) {
        if(vote==null)
        {
            return new ResponseHandler().generateResponse(
                    "Data tidak Valid",//message
                    HttpStatus.BAD_REQUEST,//httpstatus
                    null,//object
                    "FV002001",//errorCode Fail Validation modul-code 001 sequence 001 range 001 - 010
                    request
            );
        }

        try{
            Optional<Vote> find;
            find = voteRepo.findByPostAndUser(vote.getPost(),vote.getUser());
            Integer totVote ;
            if (find.isEmpty()){
                Optional<Post> post;
                post = postRepo.findById(vote.getPost().getIdPost());
                Post post1 = post.get();
                voteRepo.save(vote);
                post1.setUpvote(voteRepo.findByPostAndIsVote(vote.getPost(),true ).size());
                postRepo.save(post1);

            }else {
                Vote vote1 = find.get();
                vote1.setIsVote(vote1.getIsVote()!=vote.getIsVote());
                vote.getPost();
                voteRepo.save(vote1);
                Optional<Post> post;
                post = postRepo.findById(vote.getPost().getIdPost());
                Post post1 = post.get();
                post1.setUpvote(voteRepo.findByPostAndIsVote(vote.getPost(),true ).size());
                postRepo.save(post1);
            }

        }catch (Exception e)
        {
            strExceptionArr[1] = "save(Vote vote, HttpServletRequest request) --- LINE 59 \n"+ RequestCapture.allRequest(request);
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfiguration.getFlagLoging());
            LogTable.inputLogRequest(logRequestRepo,strExceptionArr,e,OtherConfiguration.getFlagLogTable());
            return new ResponseHandler().generateResponse(
                    "Data Gagal Disimpan",//message
                    HttpStatus.INTERNAL_SERVER_ERROR,//httpstatus
                    null,//object
                    "FE002001",//errorCode Fail Error modul-code 001 sequence 001 range 001 - 010
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
    public ResponseEntity<Object> isvote(Vote vote, HttpServletRequest request) {
        Optional<VoteDTO> voteDTO;
        Optional<Vote> find = null;
        if(vote.getPost()==null || vote.getUser()==null)
        {
            return new ResponseHandler().generateResponse(
                    "Data tidak Valid",//message
                    HttpStatus.BAD_REQUEST,//httpstatus
                    null,//object
                    "FV002001",//errorCode Fail Validation modul-code 001 sequence 001 range 001 - 010
                    request
            );
        }

        try{
            find = voteRepo.findByPostAndUser(vote.getPost(),vote.getUser());
            if (find.isEmpty()){
                Vote dto = find.get();
                dto.setIsVote(false);
                voteDTO =modelMapper.map(dto, new TypeToken<Optional<VoteDTO> >() {}.getType());

            }else {
                voteDTO =modelMapper.map(find, new TypeToken<Optional<VoteDTO> >() {}.getType());
            }

        }catch (Exception e)
        {
            Vote dt = vote;
            dt.setIsVote(false);
            dt.setPost(vote.getPost());
            dt.setUser(vote.getUser());
            voteDTO= modelMapper.map(dt, new TypeToken<Optional<VoteDTO> >() {}.getType());
            return new ResponseHandler().generateResponse(
                    "Data Berhasil Disimpan",//message
                    HttpStatus.CREATED,//httpstatus created
                    voteDTO,//object
                    null,//errorCode diisi null ketika data berhasil disimpan
                    request
            );
        }

        return new ResponseHandler().generateResponse(
                "Data Berhasil Disimpan",//message
                HttpStatus.CREATED,//httpstatus created
                voteDTO,//object
                null,//errorCode diisi null ketika data berhasil disimpan
                request
        );
    }

    @Override
    public ResponseEntity<Object> update(Long id, Vote vote, HttpServletRequest request) throws Exception {
        Optional<Vote> optionalVote;
        Vote voteTrans;
        Boolean isValid = true;

        try{
            optionalVote =  voteRepo.findById(id);

            if(optionalVote.isEmpty())
            {
                return new ResponseHandler().generateResponse(
                        "Data tidak Valid",//message
                        HttpStatus.BAD_REQUEST,//httpstatus
                        null,//object
                        "FV002011",//errorCode Fail Validation modul-code 001 sequence 001 range 011 - 020
                        request
                );
            }

            voteTrans = optionalVote.get();
            //UNTUK METHOD PATCH
//            (barangTrans.getKategoriBarang()!=null || !barangTrans.getKategoriBarang().equals(""))?barangTrans.setKategoriBarang(barang.getKategoriBarang()):barangTrans.setKategoriBarang(barangTrans.setKategoriBarang());
            voteTrans.setIsVote(vote.getIsVote());
        }
        catch (Exception e)
        {
            isValid = false;
            strExceptionArr[1] = "update(Long id, Vote vote, HttpServletRequest request) --- LINE 119 \n"+RequestCapture.allRequest(request);
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfiguration.getFlagLoging());
            LogTable.inputLogRequest(logRequestRepo,strExceptionArr,e,OtherConfiguration.getFlagLogTable());
            return new ResponseHandler().generateResponse(
                    "Data tidak Valid",//message
                    HttpStatus.BAD_REQUEST,//httpstatus
                    null,//object
                    "FV002011",//errorCode Fail Validation modul-code 001 sequence 001 range 011 - 020
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
        Optional<Vote> voteTrans =  voteRepo.findById(id);

        if(voteTrans.isEmpty())
        {
            return new ResponseHandler().generateResponse(
                    "Data tidak Valid",//message
                    HttpStatus.BAD_REQUEST,//httpstatus
                    null,//object
                    "FV002021",//errorCode Fail Validation modul-code 001 sequence 001 range 021 - 030
                    request
            );
        }

        try{
            voteRepo.deleteById(id);
        }catch (Exception e)
        {
            strExceptionArr[1] = "delete(Long id, HttpServletRequest request) --- LINE 164 \n"+RequestCapture.allRequest(request);
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfiguration.getFlagLoging());
            LogTable.inputLogRequest(logRequestRepo,strExceptionArr,e,OtherConfiguration.getFlagLogTable());
            return new ResponseHandler().generateResponse(
                    "Data Gagal Dihapus",//message
                    HttpStatus.INTERNAL_SERVER_ERROR,//httpstatus
                    null,//object
                    "FE002021",//errorCode Fail Error modul-code 001 sequence 001 range 021 - 030
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
    public ResponseEntity<Object> saveBatch(List<Vote> lt, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> findById(Long id, HttpServletRequest request) {
        Optional<Vote> voteTrans ;
        VoteDTO voteDTO;
        try{
            voteTrans  = voteRepo.findById(id);//select barang dari db
            if(voteTrans==null)
            {
                return new ResponseHandler().generateResponse(
                        "Data tidak Ditemukan",//message
                        HttpStatus.NOT_FOUND,//httpstatus
                        null,//object
                        "FV002041",//errorCode Fail Validation modul-code 001 sequence 001 range 041 - 050
                        request
                );
            }

            voteDTO = modelMapper.map(voteTrans, new TypeToken<PostDTO>() {}.getType());

        }catch (Exception e)
        {
            strExceptionArr[1] = "findById(Long id, HttpServletRequest request) --- LINE 241 \n"+RequestCapture.allRequest(request);
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfiguration.getFlagLoging());
            LogTable.inputLogRequest(logRequestRepo,strExceptionArr,e,OtherConfiguration.getFlagLogTable());
            return new ResponseHandler().generateResponse(
                    "Data tidak Valid",//message
                    HttpStatus.INTERNAL_SERVER_ERROR,//httpstatus
                    null,//object
                    "FE002041",//errorCode Fail Validation modul-code 001 sequence 001 range 041 - 050
                    request
            );
        }
        return new ResponseHandler().generateResponse(
                "Data Ditemukan",//message
                HttpStatus.OK,//httpstatus OK
                voteDTO,//object
                null,//errorCode diisi null ketika data berhasil disimpan
                request
        );
    }

    @Override
    public ResponseEntity<Object> findByPage(Integer page, Integer size, String columFirst, String valueFirst, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> findAllByPage(Integer page, Integer size, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> findAll(HttpServletRequest request) {
        List<Vote> listVote;
        List<VotePostDTO> listVoteDTO;
        try{
            listVote = voteRepo.findAll();
            listVoteDTO = modelMapper.map(listVote, new TypeToken<List<VotePostDTO>>() {}.getType());
            if(listVote.size()==0)
            {
                return new ResponseHandler().generateResponse(
                        "Data tidak Ditemukan",//message
                        HttpStatus.NOT_FOUND,//httpstatus
                        null,//object
                        "FV002071",//errorCode Fail Validation modul-code 001 sequence 001 range 071 - 080
                        request
                );
            }
        }catch (Exception e)
        {
            strExceptionArr[1] = " findAll(HttpServletRequest request) --- LINE 382 \n"+RequestCapture.allRequest(request);
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfiguration.getFlagLoging());
            return new ResponseHandler().generateResponse(
                    "Data tidak Valid",//message
                    HttpStatus.INTERNAL_SERVER_ERROR,//httpstatus
                    null,//object
                    "FE002071",//errorCode Fail Validation modul-code 001 sequence 001 range 071 - 080
                    request
            );
        }

        return new ResponseHandler().generateResponse(
                "Data Ditemukan",//message
                HttpStatus.OK,//httpstatus OK
                listVoteDTO,//object
                null,//errorCode diisi null ketika data berhasil disimpan
                request
        );
    }

    @Override
    public ResponseEntity<Object> dataToExport(MultipartFile multipartFile, HttpServletRequest request) {
        return null;
    }
}
