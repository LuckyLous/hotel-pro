package us.luckylu.dev.common.util;

import us.luckylu.dev.common.model.Pager;
import us.luckylu.dev.common.model.dto.rsp.ListResponseDto;
import us.luckylu.dev.common.model.dto.rsp.PagerResponseDto;
import us.luckylu.dev.common.model.dto.rsp.ResponseDto;

import java.util.List;

public class ResponseUtil {

    /**
     * null data ResponseDto
     *
     * @return
     */
    public static ResponseDto getSuccessRsp() {
        return getSuccessRsp(null);
    }

    /**
     * ResponseDto
     *
     * @return
     */
    public static <T> ResponseDto<T> getSuccessRsp(T data) {
        ResponseDto rspDto = new ResponseDto();
        rspDto.setCode(200);
        rspDto.setMessage("");
        if (data != null) {
            rspDto.setData(data);
        }
        return rspDto;
    }

    /**
     * null list data ListResponseDto
     *
     * @return
     */
    public static ListResponseDto getSuccessListRsp() {
        return getSuccessListRsp(null);
    }

    /**
     * list data ListResponseDto
     *
     * @return
     */
    public static <T> ListResponseDto<T> getSuccessListRsp(List<T> data) {
        ListResponseDto responseListDto = new ListResponseDto<>();
        responseListDto.setCode(200);
        responseListDto.setMessage("");
        if (data != null) {
            responseListDto.setData(data);
        }
        return responseListDto;
    }

    /**
     * pager data PagerResponseDto
     *
     * @return
     */
    public static PagerResponseDto getSuccessPagerRsp() {
        return getSuccessPagerRsp(null);
    }

    /**
     * pager data PagerResponseDto
     *
     * @return
     */
    public static <T> PagerResponseDto<T> getSuccessPagerRsp(Pager<T> pager) {
        PagerResponseDto<T> rspDto = new PagerResponseDto<>();
        rspDto.setCode(200);
        rspDto.setMessage("");
        if (pager != null) {
            rspDto.setData(pager);
        }
        return rspDto;
    }

    /**
     * error ResponseDto
     * @param code
     * @param msg
     * @return
     */
    public static ResponseDto getErrorRsp(Integer code, String msg) {
        ResponseDto rspDto = new ResponseDto();
        rspDto.setCode(code);
        rspDto.setMessage(msg);
        return rspDto;
    }
}
