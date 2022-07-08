package uz.davron.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.davron.exception.SubjectNotFoundException;
import uz.davron.response.ApiResponse;
import uz.davron.dto.SubjectDto;
import uz.davron.entity.Subject;
import uz.davron.repository.SubjectRepository;

import javax.transaction.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository repository;
    private final ModelMapper addressMapper;


    public ApiResponse save(SubjectDto subjectDto) {
        Subject map = addressMapper.map(subjectDto, Subject.class);
        return new ApiResponse(
            "Successfully add subject",
            10,
            repository.save(map)
        );

    }

    public ApiResponse deleteById(Integer id) {
        repository.findById(id).orElseThrow(SubjectNotFoundException::new);
        repository.deleteById(id);
        return new ApiResponse(
            "Successfully delete subject",
            10
        );
    }

    public ApiResponse findById(Integer id) {
        Subject subject = repository.findById(id).orElseThrow(SubjectNotFoundException::new);
        return new ApiResponse(
            "Successfully",
            11,
            subject
        );

    }

    public ApiResponse allSubject(int size, int numberOfPages) {
        Pageable page = PageRequest.of(numberOfPages, size);
        Page<Subject> all = repository.findAll(page);
        return new ApiResponse(
            "Subject by subject",
            10,
            all.getContent()
        );
    }

    public ApiResponse update(SubjectDto subjectDto, Integer id) {
        Subject map = addressMapper.map(subjectDto, Subject.class);
        map.setId(id);
        return new ApiResponse(
            "Successfully update ",
            10,
            repository.save(map)
        );

    }

}
