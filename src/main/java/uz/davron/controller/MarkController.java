package uz.davron.controller;

import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.davron.dto.MarkDto;
import uz.davron.service.MarkService;

@RequestMapping("/mark")
@RestController
@Api("mark")
public class MarkController {
    private final MarkService markService;

    public MarkController(MarkService markService) {
        this.markService = markService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Validated MarkDto markDto) {
        return ResponseEntity.ok(markService.save(markDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(markService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(markService.deleteById(id));
    }

    @GetMapping("/")
    public ResponseEntity<?> allMark(
        @RequestParam("size") int size,
        @RequestParam("number") int number
    ) {
        return ResponseEntity.ok(markService.AllMark(size, number));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Validated MarkDto markDto, @PathVariable("id") Integer id) {

        return ResponseEntity.ok(markService.update(markDto, id));


    }

}
