package com.weishop.exception;

import org.apache.log4j.Logger;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.weishop.base.BaseResponse;


@ControllerAdvice
public class ExceptionHandlerAdvice {
	private final Logger logger = Logger.getLogger(getClass());

	@ExceptionHandler({ Exception.class, RuntimeException.class })
	@ResponseBody
	public Object exception(Exception exception, WebRequest request) {
		String msg = exception.getMessage();
		logger.error(msg, exception);
		if (exception instanceof HttpRequestMethodNotSupportedException) {
			return BaseResponse.error("请求的方法不存在");
		}
		if (exception instanceof HttpMessageNotReadableException) {
			return BaseResponse.error("Json解析出错");
		}
		if (exception instanceof MissingServletRequestParameterException) {
			return BaseResponse.error("无效参数" + msg);
		}
		if (exception instanceof MethodArgumentTypeMismatchException) {
			return BaseResponse.error("参数类型转换出错" + msg);
		}
		return BaseResponse.error(msg);
	}
}
