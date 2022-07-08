package uz.davron.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.davron.dto.UniversityDto;
import uz.davron.service.UniversityService;

@RequestMapping("/university")
@RestController
@Slf4j
@Api("university")
public class UniversityController {
    private final UniversityService universityService;

    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Validated UniversityDto universityDto) {

        return ResponseEntity.ok(universityService.save(universityDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(universityService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(universityService.deleteById(id));
    }

    @GetMapping("/page-query")
    public ResponseEntity<?> allUniversity(@RequestParam("size") int size,
                                           @RequestParam("number") int number) {
        return ResponseEntity.ok(universityService.allUniversity(size, number));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Validated UniversityDto universityDto, @PathVariable("id") Integer id) {

        return ResponseEntity.ok(universityService.update(universityDto, id));
    }
}
