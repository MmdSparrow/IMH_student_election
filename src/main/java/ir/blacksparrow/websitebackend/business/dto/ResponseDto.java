package ir.blacksparrow.websitebackend.business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Data
public class ResponseDto {
    private boolean success;
    private String message;
    private int count;
    @JsonFormat
    private Object data;

    public ResponseDto(boolean success, String message, Object data){
        this.success = success;
        this.message = message;
        this.data = data;
        this.count = data==null ? -1: 1;
    }
}
