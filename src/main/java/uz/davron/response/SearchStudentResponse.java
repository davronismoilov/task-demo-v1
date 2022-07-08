package uz.davron.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchStudentResponse {
    private String firstName;
    private String lastName;
    private String group;
    private String faculty;
}
