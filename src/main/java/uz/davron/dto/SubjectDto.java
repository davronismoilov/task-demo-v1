package uz.davron.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.Size;

@ApiModel()
@Data
public class SubjectDto {
    @Size(max = 255)
    private String name;

}
