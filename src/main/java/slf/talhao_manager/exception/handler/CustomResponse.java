package slf.talhao_manager.exception.handler;

import org.springframework.http.HttpStatus;

public class CustomResponse {
    String mensagem;
    HttpStatus status;

    public CustomResponse(String mensagem, HttpStatus httpStatus){
        this.mensagem = mensagem;
        this.status = httpStatus;
    }

    public String getMensagem(){
        return mensagem;
    }

    public HttpStatus getStatus(){
        return status;
    }
}
