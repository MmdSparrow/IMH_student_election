package ir.blacksparrow.imh_student_election.view.controller;

import ir.blacksparrow.imh_student_election.business.dto.ResponseDto;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
public class ParentController {
    private ModelMapper modelMapper;

    public ParentController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    protected int getSize(int size, int offset, int length) {
        if (offset + size < length)
            return offset + size;
        else if (offset + 1 > length)
            return 0;
        else
            return length - offset;
    }

    protected ResponseEntity<ResponseDto> sendResponse(ResponseDto responseDto, HttpStatus httpStatus) {
        return new ResponseEntity<>(responseDto, httpStatus);
    }
}
