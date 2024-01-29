package projectboard.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * {
 *     "timestamp": "2024-01-29T11:00:00.123456",
 *     "status": 404,
 *     "error": "NOT_FOUND",
 *     "code": "USER_NOT_FOUND",
 *     "message": "해당 유저 정보를 찾을 수 없습니다."
 *     "validation": [
 *        {
 *            "field": "name"
 *            "message": "아이디를 입력해주세요."
 *        },
 *        {
 *            "field": "email"
 *            "message": "이메일을 입력해주세요."
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

    /**
     * Validation을 통해 바인딩을 하지 못했을때 아래와 같은 생성자를 통해 필드와 메시지를 출력
     */
    public ErrorResponse(ErrorCode errorCode, List<FieldError> errors) {
        this.message = errorCode.getMessage();
        this.status = errorCode.getStatus();
        this.code = errorCode.getCode();
        this.validation = errors;
    }
}
