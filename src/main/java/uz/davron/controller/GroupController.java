package uz.davron.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.davron.dto.GroupDto;
import uz.davron.service.GroupService;

@RequestMapping("/group")
@RestController
@Api("group")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;


    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Validated GroupDto groupDto) {

        return ResponseEntity.ok(groupService.save(groupDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(groupService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(groupService.deleteById(id));
    }

    @GetMapping("/")
    public ResponseEntity<?> allGroup(@RequestParam("size") int size,
                                      @RequestParam("number") int number) {
        return ResponseEntity.ok(groupService.allGroup(size, number));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Validated GroupDto groupDto, @PathVariable("id") Integer id) {

        return ResponseEntity.ok(groupService.update(groupDto, id));
    }

    @GetMapping("/mark/{id}")
    public ResponseEntity<?> getMarksStudentGroups(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(groupService.getMarkGroupStudent(id));
    }
}
