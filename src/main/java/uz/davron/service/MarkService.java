package uz.davron.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.davron.dto.MarkDto;
import uz.davron.entity.*;
import uz.davron.exception.*;
import uz.davron.repository.MarkRepository;
import uz.davron.repository.StudentRepository;
import uz.davron.repository.SubjectRepository;
import uz.davron.response.ApiResponse;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MarkService {
    private final MarkRepository repository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;


    public ApiResponse save(MarkDto  markDto) {
        Student student = studentRepository.findById(markDto.getStudentId()).orElseThrow(StudentNotFoundException::new);
        Subject subject = subjectRepository.findById(markDto.getSubjectId()).orElseThrow(SubjectNotFoundException::new);

        Mark mark = Mark.builder()
            .mark(markDto.getMark())
            .subject(subject)
            .student(student)
            .build();


        return new ApiResponse(
            "Successfully add mark",
            10,
            repository.save(mark)
        );
    }

    public ApiResponse deleteById(Integer id) {
        repository.findById(id).orElseThrow(StudentNotFoundException::new);
        repository.deleteById(id);
        return new ApiResponse(
            "Successfully delete mark",
            10
        );
    }

    public ApiResponse findById(Integer id) {
        Mark mark = repository.findById(id).orElseThrow(MarkNotFoundException::new);
        return new ApiResponse(
            "Successfully",
            11,
            mark
        );

    }

    public ApiResponse AllMark(int size, int numberOfPages) {
        Pageable page = PageRequest.of(numberOfPages, size);
        Page<Mark> all = repository.findAll(page);
        return new ApiResponse(
            "Marks by page",
            10,
            all.getContent()
        );
    }



    public ApiResponse update(MarkDto markDto, Integer id) {

        Student student = studentRepository.findById(markDto.getStudentId()).orElseThrow(StudentNotFoundException::new);
        Subject subject = subjectRepository.findById(markDto.getSubjectId()).orElseThrow(SubjectNotFoundException::new);

        Mark mark = Mark.builder()
            .id(id)
            .mark(markDto.getMark())
            .subject(subject)
            .student(student)
            .build();


        return new ApiResponse(
            "Successfully mark",
            10,
            repository.save(mark)
        );
    }
}
