package uz.davron.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import springfox.documentation.service.ApiInfo;
import uz.davron.exception.UniversityNotFoundException;
import uz.davron.response.ApiResponse;
import uz.davron.dto.FacultyDto;
import uz.davron.entity.Faculty;
import uz.davron.entity.University;

import uz.davron.exception.FacultyNotFoundException;
import uz.davron.repository.FacultyRepository;
import uz.davron.repository.UniversityRepository;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FacultyService {
    private final FacultyRepository repository;
    private final UniversityRepository universityRepository;


    public ApiResponse save(FacultyDto facultyDto) {
        University university = universityRepository.findById(
            facultyDto.getUniversityId()
        ).orElseThrow(UniversityNotFoundException::new);

        Faculty faculty = Faculty.builder()
            .name(facultyDto.getName())
            .university(university)
            .build();

        return new ApiResponse(
            "successfully add new faculty",
            10,
            repository.save(faculty)
        );

    }

    public ApiResponse deleteById(Integer id) {
        repository.findById(id).orElseThrow(FacultyNotFoundException::new);
        repository.deleteById(id);
        return new ApiResponse(
            "Successfully delete faculty",
            10
        );
    }

    public ApiResponse findById(Integer id) {
        Faculty faculty = repository.findById(id).orElseThrow(FacultyNotFoundException::new);
        return new ApiResponse(
            "Successfully",
            11,
            faculty
        );

    }

    public ApiResponse allFaculty(int size, int numberOfPages) {
        Pageable page = PageRequest.of(numberOfPages, size);
        Page<Faculty> all = repository.findAll(page);
        return new ApiResponse(
            "Faculty by page",
            10,
            all.getContent()
        );
    }

    public ApiResponse update(FacultyDto facultyDto, Integer id) {
        University university = universityRepository.findById(
            facultyDto.getUniversityId()
        ).orElseThrow(UniversityNotFoundException::new);

        Faculty faculty = Faculty.builder()
            .name(facultyDto.getName())
            .university(university)
            .build();
        faculty.setId(id);
        return new ApiResponse(
            "Successfully update ",
            10,
            repository.save(faculty)
        );

    }

    public ApiResponse findGroupAndStudentCountById(int id) {
        return new ApiResponse(
            "Groups and count",
            10,
            repository.findByIdFaculty(id)
        );
    }
}
