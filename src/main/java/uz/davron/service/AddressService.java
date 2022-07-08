package uz.davron.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.davron.response.ApiResponse;
import uz.davron.dto.AddressDto;
import uz.davron.exception.AddressNotFoundException;
import uz.davron.entity.Address;
import uz.davron.repository.AddressRepository;

import javax.transaction.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository repository;
    private final ModelMapper addressMapper;


    public ApiResponse save(AddressDto addressDto) {
        Address map = addressMapper.map(addressDto, Address.class);
        return new ApiResponse(
            "Successfully add address",
            10,
            repository.save(map)
        );

    }

    public ApiResponse deleteById(Integer id) {
        repository.findById(id).orElseThrow(AddressNotFoundException::new);
        repository.deleteById(id);
        return new ApiResponse(
            "Successfully delete address",
            10
        );
    }

    public ApiResponse findById(Integer id) {
        Address address = repository.findById(id).orElseThrow(AddressNotFoundException::new);
        return new ApiResponse(
            "Successfully",
            11,
            address
        );

    }

    public ApiResponse allAddress(int size, int numberOfPages) {
        Pageable page = PageRequest.of(numberOfPages, size);
        Page<Address> all = repository.findAll(page);
        return new ApiResponse(
            "Address by page",
            10,
            all.getContent()
        );
    }

    public ApiResponse update(AddressDto addressDto, Integer id) {
        Address map = addressMapper.map(addressDto, Address.class);
        map.setId(id);
        return new ApiResponse(
            "Successfully update ",
            10,
            repository.save(map)
        );

    }
}
