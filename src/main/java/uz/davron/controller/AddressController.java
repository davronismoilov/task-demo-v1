package uz.davron.controller;

import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.davron.dto.AddressDto;
import uz.davron.service.AddressService;


@RequestMapping("/api/address")
@RestController
@Api("address")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Validated AddressDto addressDto) {
        return ResponseEntity.ok(addressService.save(addressDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(addressService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(addressService.deleteById(id));
    }

    @GetMapping("/")
    public ResponseEntity<?> allAddress(
        @RequestParam("size") int size,
        @RequestParam("number") int number) {

        return ResponseEntity.ok(addressService.allAddress(size, number));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Validated AddressDto addressDto, @PathVariable("id") Integer id) {
        return ResponseEntity.ok(addressService.update(addressDto, id));
    }
}
