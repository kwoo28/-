package projectboard.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

//https://blog.naver.com/hj_kim97/222838956315
@Slf4j
@ControllerAdvice(basePackages = "projectboard.controller")
public class GlobalExceptionHandler {

    /**
     * validation을 위반할 때 발생
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        //BindingResult의 필드 오류 값을 넣을 배열
        List<ErrorResponse.FieldError> errors = new ArrayList<>();
        for(FieldError fieldError : e.getFieldErrors()) {
            log.error("name:{}, message:{}", fieldError.getField(), fieldError.getDefaultMessage());

            ErrorResponse.FieldError error = ErrorResponse.FieldError.builder().
                    field(fieldError.getField()).
                    message(fieldError.getDefaultMessage()).
                    build();

            errors.add(error);
        }

        ErrorResponse response = new ErrorResponse(ErrorCode.INVALID_INPUT_VALUE, errors);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatus()));
    }

    /**
     * mybatis를 통해 DB 조작시 발생
     * DataIntegrityViolationException : 삽입하거나 수정하려는 데이터의 무결성 제약 조건을 위반할 때 발생
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(Exception e) {
        log.error("handleDataIntegrityViolationException = {}",e.getMessage());
        ErrorResponse response = new ErrorResponse(ErrorCode.DUPLICATE_RESOURCE);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    /**
     * 메소드의 인자가 예상한 타입과 일치하지 않을 때 발생
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        ErrorResponse response = new ErrorResponse(ErrorCode.BAD_REQUEST);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    /**
     * 지원하지 않은 HTTP method 호출 할 경우 발생
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException = {}",e.getMessage());
        ErrorResponse response = new ErrorResponse(ErrorCode.METHOD_NOT_ALLOWED);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    /**
     * Authentication 객체가 필요한 권한을 보유하지 않은 경우 발생합
     */
    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException e) {
        log.error("handleAccessDeniedException = {}",e.getMessage());
        ErrorResponse response = new ErrorResponse(ErrorCode.ACCESS_DENIED);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    /**
     * 회원 정보를 찾지 못했을때 발생
     */
    @ExceptionHandler(value = { UserNotFoundException.class })
    protected ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e) {
        log.error("handleUserNotFoundException = {}",e.getMessage());
        ErrorResponse response = new ErrorResponse(ErrorCode.USER_NOT_FOUND);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    /**
     * 메세지 정보를 찾지 못했을때 발생
     */
    @ExceptionHandler(value = { MessageNotFoundException.class })
    protected ResponseEntity<ErrorResponse> handleMessageNotFoundException(MessageNotFoundException e) {
        log.error("handleMessageNotFoundException = {}",e.getMessage());
        ErrorResponse response = new ErrorResponse(ErrorCode.MESSAGE_NOT_FOUND);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    /**
     * 게시글 정보를 찾지 못했을때 발생
     */
    @ExceptionHandler(value = { PostNotFoundException.class })
    protected ResponseEntity<ErrorResponse> handlePostNotFoundException(PostNotFoundException e) {
        log.error("handlePostNotFoundException = {}",e.getMessage());
        ErrorResponse response = new ErrorResponse(ErrorCode.POST_NOT_FOUND);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    /**
     * 태그 정보를 찾지 못했을때 발생
     */
    @ExceptionHandler(value = { TagNotFoundException.class })
    protected ResponseEntity<ErrorResponse> handleTagNotFoundException(TagNotFoundException e) {
        log.error("handleTagNotFoundException = {}",e.getMessage());
        ErrorResponse response = new ErrorResponse(ErrorCode.TAG_NOT_FOUND);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    /**
     * 위에 해당하는 예외에 해당하지 않을 때 모든 예외를 처리하는 메소드
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        e.printStackTrace();
        ErrorResponse response = new ErrorResponse(ErrorCode.SERVER_ERROR);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}