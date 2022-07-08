package uz.davron.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import uz.davron.entity.Faculty;

@ApiModel()
@Data
public class GroupDto   {

    private String name;
    private int facultyId;
    private int year;

}
