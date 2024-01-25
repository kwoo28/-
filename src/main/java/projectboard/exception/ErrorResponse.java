package projectboard.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * {
 *     "timestamp": "2022-08-16T11:00:00.123456",
 *     "status": 400,
 *     "error": "BAD_REQUEST",
 *     "code": "INVALID_INPUT_VALUE",
 *     "message": "입력값이 올바르지 않습니다."
 *     "validation": [
 *        {
 *            "field": "name"
 *            "message": "이름을 입력해주세요."
 *        },
 *        {
 *            "field": "age"
 *            "message": "나이는 10 ~ 30살 이어야 합니다."
 *        }
 *     ],
 * }
 */
@Getter
public class ErrorResponse {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private String message;
    private int status;
    private List<FieldError> validation;
    private String code;

     //validation의 bindingError 값을 넣어줌.
    @Getter
    public static class FieldError {
        private String field;
        private String message;

        @Builder
        public FieldError(String field, String message) {
            this.field = field;
            this.message = message;
        }
    }

    public ErrorResponse(ErrorCode errorCode) {
        this.message = errorCode.getMessage();
        this.status = errorCode.getStatus();
        this.code = errorCode.getCode();
    }

    public ErrorResponse(ErrorCode errorCode, List<FieldError> errors) {
        this.message = errorCode.getMessage();
        this.status = errorCode.getStatus();
        this.code = errorCode.getCode();
        this.validation = errors;
    }
}
