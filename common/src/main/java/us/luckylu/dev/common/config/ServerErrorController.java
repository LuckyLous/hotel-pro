package us.luckylu.dev.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.NestedServletException;
import us.luckylu.dev.common.exception.BaseException;
import us.luckylu.dev.common.exception.BaseExceptionEnum;
import us.luckylu.dev.common.exception.BaseJsonSerializeHandlerExceptionResolver;
import us.luckylu.dev.common.model.dto.rsp.ResponseDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 错误返回的JSON信息
 */
@Controller
@RequestMapping(value = "error")
@EnableConfigurationProperties({ServerProperties.class})
public class ServerErrorController implements ErrorController {

    private ErrorAttributes errorAttributes;

    @Autowired
    private ServerProperties serverProperties;

    /**
     * 初始化ExceptionController
     * @param errorAttributes
     */
    @Autowired
    public ServerErrorController(ErrorAttributes errorAttributes) {
        Assert.notNull(errorAttributes, "ErrorAttributes must not be null");
        this.errorAttributes = errorAttributes;
    }


    /**
     * 定义404的ModelAndView
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(produces = "text/html",value = "404")
    public ModelAndView errorHtml404(HttpServletRequest request,
                                     HttpServletResponse response) {
        response.setStatus(getStatus(request).value());
        Map<String, Object> model = getErrorAttributes(request,
                isIncludeStackTrace(request, MediaType.TEXT_HTML));
        return new ModelAndView("error/404", model);
    }

    /**
     * 定义404的JSON数据
     * @param request
     * @return
     */
    @RequestMapping(value = "404")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> error404(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request,
                isIncludeStackTrace(request, MediaType.TEXT_HTML));
        HttpStatus status = getStatus(request);
        return new ResponseEntity<Map<String, Object>>(body, status);
    }

    /**
     * 定义500的ModelAndView
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(produces = "text/html",value = "500")
    public ModelAndView errorHtml500(HttpServletRequest request,
                                     HttpServletResponse response) {
        response.setStatus(getStatus(request).value());
        Map<String, Object> model = getErrorAttributes(request,
                isIncludeStackTrace(request, MediaType.TEXT_HTML));
        return new ModelAndView("error/500", model);
    }

    @Bean
    public BaseJsonSerializeHandlerExceptionResolver baseJsonSerializeHandlerExceptionResolver() {
        return new BaseJsonSerializeHandlerExceptionResolver();
    }

    @Bean
    public RedisTemplate operations(RedisConnectionFactory connectionFactory){
        RedisTemplate<Object, Object> template = new RedisTemplate();
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setConnectionFactory(connectionFactory);
        template.afterPropertiesSet();
        return template ;
    }

//    /**
//     * 定义500的错误JSON信息
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = "500")
//    @ResponseBody
//    public ResponseEntity<Map<String, Object>> error500(HttpServletRequest request) {
//        Map<String, Object> body = getErrorAttributes(request,
//                isIncludeStackTrace(request, MediaType.TEXT_HTML));
//        HttpStatus status = getStatus(request);
//        return new ResponseEntity<Map<String, Object>>(body, status);
//    }

    /**
     * 定义异常JSON信息
     * @param request
     * @return
     */
    @RequestMapping
    @ResponseBody
    public ResponseDto error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.TEXT_HTML));
        HttpStatus status = getStatus(request);
        ResponseDto responseDto = new ResponseDto();

        Object exception = request.getAttribute("javax.servlet.error.exception");
        if (exception instanceof BaseException) {
            responseDto.setErrCode(((BaseException) exception).getCode());
            responseDto.setMessage(((BaseException) exception).getMessage());
            return responseDto;
        }

        if (exception instanceof NestedServletException) {
            Throwable cause = ((NestedServletException) exception).getCause();

            if (cause instanceof BaseException) {
                responseDto.setErrCode(((BaseException) cause).getCode());
                responseDto.setMessage(((BaseException) cause).getMessage());
                return responseDto;
            }
        }

        responseDto.setErrCode(BaseExceptionEnum.SYSTEM_BUSY.getCode());
        responseDto.setMessage(String.format("%s:%s", body.get("status"), body.get("error")));
        return responseDto;
    }

    /**
     * Determine if the stacktrace attribute should be included.
     * @param request the source request
     * @param produces the media type produced (or {@code MediaType.ALL})
     * @return if the stacktrace attribute should be included
     */
    protected boolean isIncludeStackTrace(HttpServletRequest request,
                                          MediaType produces) {
        ErrorProperties.IncludeStacktrace include = this.serverProperties.getError().getIncludeStacktrace();
        if (include == ErrorProperties.IncludeStacktrace.ALWAYS) {
            return true;
        }
        if (include == ErrorProperties.IncludeStacktrace.ON_TRACE_PARAM) {
            return getTraceParameter(request);
        }
        return false;
    }


    /**
     * 获取错误的信息
     * @param request
     * @param includeStackTrace
     * @return
     */
    private Map<String, Object> getErrorAttributes(HttpServletRequest request,
                                                   boolean includeStackTrace) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        return this.errorAttributes.getErrorAttributes(requestAttributes,
                includeStackTrace);
    }

    /**
     * 是否包含trace
     * @param request
     * @return
     */
    private boolean getTraceParameter(HttpServletRequest request) {
        String parameter = request.getParameter("trace");
        if (parameter == null) {
            return false;
        }
        return !"false".equals(parameter.toLowerCase());
    }

    /**
     * 获取错误编码
     * @param request
     * @return
     */
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        }
        catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }


    @Override
    public String getErrorPath() {
        return null;
    }
}