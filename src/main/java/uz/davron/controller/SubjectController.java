package uz.davron.controller;

import io.swagger.annotations.Api;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.davron.dto.SubjectDto;
import uz.davron.service.SubjectService;


@RequestMapping("/subject")
@RestController

@Api("subject")
public class SubjectController {
    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Validated SubjectDto subjectDto) {

        return ResponseEntity.ok(subjectService.save(subjectDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(subjectService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(subjectService.deleteById(id));
    }

    @GetMapping("/")
    public ResponseEntity<?> allSubjects(@RequestParam("size") int size,
                                         @RequestParam("number") int number) {

        return ResponseEntity.ok(subjectService.allSubject(size, number));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Validated SubjectDto subjectDto, @PathVariable("id") Integer id) {
        return ResponseEntity.ok(subjectService.update(subjectDto, id));
    }
}
