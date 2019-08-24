package us.luckylu.dev.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import us.luckylu.dev.client.model.Gem;
import us.luckylu.dev.client.model.req.DocumentSearchReq;
import us.luckylu.dev.client.model.req.GemCreateReq;
import us.luckylu.dev.common.model.dto.rsp.ListResponseDto;
import us.luckylu.dev.common.model.dto.rsp.ResponseDto;

import java.io.IOException;

/**
 * @author lu
 * @create 2019-03-28 13:53
 */
@Slf4j
@RestController
@RequestMapping("es")
public class ElasticSearchController {


    @PostMapping("create")
    public ResponseDto batchCreate(@Validated @RequestBody GemCreateReq req){
        return null;
    }

    @GetMapping("search")
    public ListResponseDto<Gem> search(DocumentSearchReq documentSearchReq) throws IOException {
        return null;
    }



}
