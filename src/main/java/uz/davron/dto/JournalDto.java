package uz.davron.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import uz.davron.entity.Group;

import java.util.List;

@ApiModel()
@Data
public class JournalDto  {

    private String name;
    private int groupId;
    private List<Integer> subjects;


}
