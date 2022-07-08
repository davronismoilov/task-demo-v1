package uz.davron.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import uz.davron.entity.Subject;
import uz.davron.exception.JournalNotFoundException;
import uz.davron.repository.SubjectRepository;
import uz.davron.response.ApiResponse;
import uz.davron.dto.JournalDto;
import uz.davron.entity.Group;
import uz.davron.entity.Journal;
import uz.davron.exception.GroupNotFoundException;
import uz.davron.repository.GroupRepository;
import uz.davron.repository.JournalRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class JournalService {
    private final JournalRepository repository;
    private final GroupRepository groupRepository;
    private final SubjectRepository subjectRepository;

    public ApiResponse save(JournalDto journalDto) {
        Group group = groupRepository.findById(
            journalDto.getGroupId()
        ).orElseThrow(GroupNotFoundException::new);

        List<Subject> subjects = new ArrayList<>();

        for (Integer subject : journalDto.getSubjects()) {
            Optional<Subject> subject1 = subjectRepository.findById(subject);
            subject1.ifPresent(subjects::add);
        }


        Journal build = Journal.builder()
            .name(journalDto.getName())
            .group(group)
            .subjects(subjects)
            .build();


        return new ApiResponse(
            "successfully add new journal",
            10,
            repository.save(build)
        );

    }

    public ApiResponse deleteById(Integer id) {
        repository.findById(id).orElseThrow(JournalNotFoundException::new);
        repository.deleteById(id);
        return new ApiResponse(
            "Successfully delete journal",
            10
        );
    }

    public ApiResponse findById(Integer id) {
        Journal journal = repository.findById(id).orElseThrow(JournalNotFoundException::new);
        return new ApiResponse(
            "Successfully",
            11,
            journal
        );

    }

    public ApiResponse allJournal(int size, int numberOfPages) {
        Pageable page = PageRequest.of(numberOfPages, size);
        Page<Journal> all = repository.findAll(page);
        return new ApiResponse(
            "journal by page",
            10,
            all.getContent()
        );
    }

    public ApiResponse update(JournalDto journalDto, Integer id) {
        Group group = groupRepository.findById(
            journalDto.getGroupId()
        ).orElseThrow(GroupNotFoundException::new);

        List<Subject> subjects = new ArrayList<>();

        for (Integer subject : journalDto.getSubjects()) {
            Optional<Subject> subject1 = subjectRepository.findById(subject);
            subject1.ifPresent(subjects::add);
        }
        Journal build = Journal.builder()
            .id(id)
            .name(journalDto.getName())
            .group(group)
            .subjects(subjects)
            .build();

        return new ApiResponse(
            "successfully add update",
            10,
            repository.save(build)
        );
    }

}
