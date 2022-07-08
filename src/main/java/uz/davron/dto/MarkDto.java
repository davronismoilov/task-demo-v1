package uz.davron.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import uz.davron.entity.Student;
import uz.davron.entity.Subject;

@ApiModel()
@Data
public class MarkDto   {
    private int studentId;
    private int subjectId;
    private int mark;

}
