package uz.davron.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ApiModel()
@Data
public class AddressDto {

    @Size(max = 255)
    @NotBlank
    private String city;
    @Size(max = 255)
    @NotBlank
    private String district;
    @Size(max = 255)
    @NotBlank
    private String street;


}
