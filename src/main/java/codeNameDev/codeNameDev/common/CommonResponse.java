package codeNameDev.codeNameDev.common;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class CommonResponse<T> {

    private boolean isSuccess;
    private String code;
    private String message;
    private T result;

    public static <T> CommonResponse<T> success(String message, T result) {
        return new CommonResponse<>(true, "200", message, result);
    }

    public static <T> CommonResponse<T> success(T result) {
        return new CommonResponse<>(true, "200", "요청에 성공하였습니다.", result);
    }
}

