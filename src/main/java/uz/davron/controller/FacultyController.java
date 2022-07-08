package uz.davron.controller;

import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.davron.dto.FacultyDto;
import uz.davron.service.FacultyService;

@RequestMapping("/faculty")
@RestController
@Api("faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Validated FacultyDto facultyDto) {

        return ResponseEntity.ok(facultyService.save(facultyDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(facultyService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(facultyService.deleteById(id));
    }

    @GetMapping("/")
    public ResponseEntity<?> allFaculty( @RequestParam("size") int size,
                                              @RequestParam("number") int number) {
        return  ResponseEntity.ok(facultyService.allFaculty(size , number));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Validated FacultyDto facultyDto, @PathVariable("id") Integer id) {

        return ResponseEntity.ok(facultyService.update(facultyDto, id));
    }

    @GetMapping("/group/{id}")
    public ResponseEntity<?> findGroupAndStudentCountById(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(facultyService.findGroupAndStudentCountById(id));
    }
}
