package us.luckylu.dev.common.exception;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BaseJsonSerializeHandlerExceptionResolver extends AbstractHandlerExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(BaseJsonSerializeHandlerExceptionResolver.class);

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        logger.error("异常统一处理", ex);

        ResponseBody rexist = ((HandlerMethod) handler).getMethod().getAnnotation(ResponseBody.class);
        RestController rcexist = ((HandlerMethod) handler).getBeanType().getAnnotation(RestController.class);

        if (rexist != null || rcexist != null) {
            String message = ex.getMessage();
            Integer code = 500;

            // Hibernate Validator 参数校验错误抛出的异常
            if (ex instanceof BindException) {
                FieldError fieldError = ((BindException) ex).getBindingResult().getFieldError();
                message = fieldError.getDefaultMessage();
                code = BaseExceptionEnum.INVALID_PARAMEGER.getCode();
            }

            // 方法参数校验错误抛出的异常
            if (ex.getCause() instanceof ValidationException) {
                ValidationException e = (ValidationException) ex.getCause();
                message = e.getMessage();
                code = BaseExceptionEnum.INVALID_PARAMEGER.getCode();
            }
            if (ex instanceof MethodArgumentNotValidException) {
                message = ((MethodArgumentNotValidException) ex).getBindingResult().getFieldError().getDefaultMessage();
                code = BaseExceptionEnum.INVALID_PARAMEGER.getCode();
            }
            if (ex instanceof ConstraintViolationException) {
                Set<ConstraintViolation<?>> violationSet = ((ConstraintViolationException) ex).getConstraintViolations();
                for (ConstraintViolation validator : violationSet) {
                    message = validator.getMessage();
                    break;
                }
                code = BaseExceptionEnum.INVALID_PARAMEGER.getCode();
            }

            // 应用异常
            if (ex instanceof BaseException) {
                message = ex.getMessage();
                code = ((BaseException) ex).getCode();
            }

            FastJsonJsonView jsonView = new FastJsonJsonView();

            Map<String, Object> map = new HashMap<>();
            map.put("errCode", code);
            map.put("message", message);
            return new ModelAndView(jsonView).addObject("status", map);
        }

        return null;
    }
}
