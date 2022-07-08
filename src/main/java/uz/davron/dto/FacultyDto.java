package uz.davron.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel()
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FacultyDto  {
    private String name;
    private int universityId;




}
