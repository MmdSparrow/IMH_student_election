package ir.blacksparrow.websitebackend.repository;

import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ParentRepository {
    private ModelMapper modelMapper;

    public ParentRepository(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    protected <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());

    }
}
