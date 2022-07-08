package uz.davron.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import uz.davron.entity.Address;

@ApiModel()
@Data
public class UniversityDto  {
    private String name;
    private AddressDto address;
    private int openYear;

}
