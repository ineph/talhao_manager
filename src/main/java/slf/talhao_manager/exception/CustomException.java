package slf.talhao_manager.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {
    private final String mensagem;
    private final HttpStatus httpStatus;

    public CustomException(String mensagem, HttpStatus httpStatus) {
        this.mensagem = mensagem;
        this.httpStatus = httpStatus;
    }

    public String getMensagem() {
        return mensagem;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
