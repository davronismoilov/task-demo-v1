package uz.davron.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.davron.exception.UniversityNotFoundException;
import uz.davron.response.ApiResponse;

import uz.davron.dto.UniversityDto;
import uz.davron.entity.Address;
import uz.davron.entity.University;
import uz.davron.repository.UniversityRepository;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UniversityService {
    private final UniversityRepository repository;
    private final AddressService addressService;


    public ApiResponse save(UniversityDto  universityDto) {
        ApiResponse save = addressService.save(universityDto.getAddress());
        University build = University.builder()
            .address((Address) save.getData())
            .name(universityDto.getName())
            .openYear(universityDto.getOpenYear())
            .build();

        return new ApiResponse(
            "Successfully",
            10,
            repository.save(build)
        );
    }

    public ApiResponse deleteById(Integer id) {
        repository.findById(id).orElseThrow(UniversityNotFoundException::new);
        repository.deleteById(id);
        return new ApiResponse(
            "Successfully delete university",
            10
        );
    }

    public ApiResponse findById(Integer id) {
        University university = repository.findById(id).orElseThrow(UniversityNotFoundException::new);
        return new ApiResponse(
            "Successfully",
            11,
            university
        );

    }

    public ApiResponse allUniversity(int size, int numberOfPages) {
        Pageable page = PageRequest.of(numberOfPages, size);
        Page<University> all = repository.findAll(page);
        return new ApiResponse(
            "University by page",
            10,
            all.getContent()
        );
    }



    public ApiResponse update(UniversityDto universityDto, Integer id) {

        ApiResponse save = addressService.save(universityDto.getAddress());
        University build = University.builder()
            .id(id)
            .address((Address) save.getData())
            .name(universityDto.getName())
            .openYear(universityDto.getOpenYear())
            .build();

        return new ApiResponse(
            "Successfully",
            10,
            build
        );
    }
}
