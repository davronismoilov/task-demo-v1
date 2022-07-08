package uz.davron.controller;

import io.swagger.annotations.Api;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.davron.dto.StudentDto;
import uz.davron.service.StudentService;


@RequestMapping("/student")
@RestController
@Api("student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Validated StudentDto studentDto) {

        return ResponseEntity.ok(studentService.save(studentDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(studentService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentService.deleteById(id));
    }

    @GetMapping("/")
    public ResponseEntity<?> allStudents(@RequestParam("size") int size,
                                         @RequestParam("number") int number) {
        return ResponseEntity.ok(studentService.allStudent(size , number));
    }



    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Validated StudentDto studentDto, @PathVariable("id") Integer id) {

        return ResponseEntity.ok(studentService.update(studentDto, id));
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchByFirstname(@RequestParam("name") String name){
        return  ResponseEntity.ok(studentService.searchStudentByName(name));
    }

    @GetMapping("/subject/{id}")
    public ResponseEntity<?> findSubjectById(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(studentService.findSubjectByStudentId(id));
    }
}
