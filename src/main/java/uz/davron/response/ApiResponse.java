package uz.davron.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private String message;
    private int status;
    private Object data;

    public ApiResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }
}
