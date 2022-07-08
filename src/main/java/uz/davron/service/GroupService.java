package uz.davron.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.davron.response.ApiResponse;
import uz.davron.dto.GroupDto;
import uz.davron.entity.Faculty;
import uz.davron.entity.Group;
import uz.davron.exception.FacultyNotFoundException;
import uz.davron.exception.GroupNotFoundException;
import uz.davron.repository.FacultyRepository;
import uz.davron.repository.GroupRepository;
import uz.davron.response.GroupStudentsMark;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository repository;
    private final FacultyRepository facultyRepository;


    public ApiResponse save(GroupDto groupDto) {
        Faculty faculty = facultyRepository.findById(
            groupDto.getFacultyId()
        ).orElseThrow(FacultyNotFoundException::new);

        Group group = Group.builder()
            .name(groupDto.getName())
            .faculty(faculty)
            .year(groupDto.getYear())
            .build();
        return new ApiResponse(
            "successfully add new group",
            10,
            repository.save(group)
        );

    }

    public ApiResponse deleteById(Integer id) {
        repository.findById(id).orElseThrow(GroupNotFoundException::new);
        repository.deleteById(id);
        return new ApiResponse(
            "Successfully delete group",
            10
        );
    }

    public ApiResponse findById(Integer id) {
        Group group = repository.findById(id).orElseThrow(GroupNotFoundException::new);
        return new ApiResponse(
            "Successfully",
            11,
            group
        );

    }

    public ApiResponse allGroup(int size, int numberOfPages) {
        Pageable page = PageRequest.of(numberOfPages, size);
        Page<Group> all = repository.findAll(page);
        return new ApiResponse(
            "groups by page",
            10,
            all.getContent()
        );
    }

    public ApiResponse update(GroupDto groupDto, Integer id) {
        Faculty faculty = facultyRepository.findById(
            groupDto.getFacultyId()
        ).orElseThrow(FacultyNotFoundException::new);

        Group group = Group.builder()
            .id(id)
            .name(groupDto.getName())
            .faculty(faculty)
            .year(groupDto.getYear())
            .build();

        return new ApiResponse(
            "successfully update group",
            10,
            repository.save(group)
        );

    }

    public ApiResponse getMarkGroupStudent(int id) {
        List<GroupStudentsMark> groupStudentsMarks = repository.groupStudentsMark(id);

        //  Stream<GroupStudentsMark> sorted = groupStudentsMarks.stream().sorted(Comparator.comparing((GroupStudentsMark::getAvg)));
        return new ApiResponse(
            "average marks student",
            10,
            groupStudentsMarks
        );
    }
}
