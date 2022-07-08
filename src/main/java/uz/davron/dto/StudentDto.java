package uz.davron.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import uz.davron.entity.Address;
import uz.davron.entity.Group;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ApiModel()
@Data
public class StudentDto  {

    @Size(max = 255)
    @NotBlank
    private String firstName;
    @Size(max = 255)
    @NotBlank
    private String lastName;
    private AddressDto address;
    private int groupId;

}
