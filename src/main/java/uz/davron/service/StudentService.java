package uz.davron.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import springfox.documentation.service.ApiInfo;
import uz.davron.dto.StudentDto;
import uz.davron.entity.Address;
import uz.davron.entity.Group;
import uz.davron.entity.Student;
import uz.davron.entity.Subject;
import uz.davron.exception.GroupNotFoundException;
import uz.davron.exception.StudentNotFoundException;
import uz.davron.repository.GroupRepository;
import uz.davron.repository.StudentRepository;
import uz.davron.repository.SubjectRepository;
import uz.davron.response.ApiResponse;
import uz.davron.response.SearchStudentResponse;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository repository;
    private final AddressService addressService;
    private final GroupRepository groupRepository;


    public ApiResponse save(StudentDto studentDto) {
        ApiResponse address = addressService.save(studentDto.getAddress());
        Group group = groupRepository.findById(studentDto.getGroupId()).orElseThrow(GroupNotFoundException::new);
        Student student = Student.builder()
            .firstName(studentDto.getFirstName())
            .lastName(studentDto.getLastName())
            .address((Address) address.getData())
            .group(group)
            .build();


        return new ApiResponse(
            "Successfully add new student",
            10,
            repository.save(student)
        );
    }

    public ApiResponse deleteById(Integer id) {
        repository.findById(id).orElseThrow(StudentNotFoundException::new);
        repository.deleteById(id);
        return new ApiResponse(
            "Successfully delete student",
            10
        );
    }

    public ApiResponse findById(Integer id) {
        Student st = repository.findById(id).orElseThrow(StudentNotFoundException::new);
        return new ApiResponse(
            "Successfully",
            11,
            st
        );

    }

    public ApiResponse allStudent(int size, int numberOfPages) {
        Pageable page = PageRequest.of(numberOfPages, size);
        Page<Student> all = repository.findAll(page);
        return new ApiResponse(
            "Students by page",
            10,
            all.getContent()
        );
    }


    public ApiResponse update(StudentDto studentDto, Integer id) {

        ApiResponse address = addressService.save(studentDto.getAddress());
        Group group = groupRepository.findById(studentDto.getGroupId()).orElseThrow(GroupNotFoundException::new);
        Student student = Student.builder()
            .id(id)
            .firstName(studentDto.getFirstName())
            .lastName(studentDto.getLastName())
            .address((Address) address.getData())
            .group(group)
            .build();


        return new ApiResponse(
            "Successfully update",
            10,
            repository.save(student)
        );
    }

    public ApiResponse searchStudentByName(String name) {
        List<SearchStudentResponse> responses = new ArrayList<>();
        for (Student student : repository.findByFirstNameIsLikeIgnoreCaseAllIgnoreCase(name)) {
            SearchStudentResponse response = new SearchStudentResponse(
                student.getFirstName(),
                student.getLastName(),
                student.getGroup().getName(),
                student.getGroup().getFaculty().getName());
            responses.add(response);

        }

        return new ApiResponse(
            "Students",
            10,
            responses
        );

    }

    public ApiResponse findSubjectByStudentId(int id){

        return new ApiResponse(
            "Subjects ",
            10,
            repository.findByIdSubjects(id)
        );
    }


}
