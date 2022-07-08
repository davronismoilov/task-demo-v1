package uz.davron.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.davron.dto.JournalDto;

import uz.davron.service.JournalService;

@RequestMapping("/journal")
@RestController
@Api("journal")
@RequiredArgsConstructor
public class JournalController {
    private final JournalService journalService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Validated JournalDto journalDto) {

        return ResponseEntity.ok(journalService.save(journalDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(journalService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(journalService.deleteById(id));
    }

    @GetMapping("/")
    public ResponseEntity<?> allJournal(@RequestParam("size") int size,
                                         @RequestParam("number") int number) {
        return ResponseEntity.ok(journalService.allJournal(size , number));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Validated JournalDto journalDto, @PathVariable("id") Integer id) {

        return ResponseEntity.ok(journalService.update(journalDto, id));
    }
}
