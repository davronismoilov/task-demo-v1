package uz.davron.handler;



import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import uz.davron.exception.*;
import uz.davron.response.ErrorMessageResponse;

import java.util.Date;


@RequiredArgsConstructor
@RestControllerAdvice
public class ApplicationExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

    @ExceptionHandler(value = {IllegalArgumentException.class,ClassCastException.class, IllegalStateException.class,
            InvalidDataAccessApiUsageException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessageResponse handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request){
        logger.warn(ex.getMessage());
        return new ErrorMessageResponse(
                HttpStatus.NOT_IMPLEMENTED.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
    }




    @ExceptionHandler(value = AddressNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessageResponse handleAddressNotFoundException (AddressNotFoundException ex, WebRequest request){

        return new ErrorMessageResponse(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                "Address not found",
                request.getDescription(false)
        );
    }

    @ExceptionHandler(value = FacultyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessageResponse handleFacultyNotFoundException(FacultyNotFoundException ex, WebRequest request){

        return new ErrorMessageResponse(
            HttpStatus.NOT_FOUND.value(),
            new Date(),
            "Faculty not found",
            request.getDescription(false)
        );
    }


    @ExceptionHandler(value = GroupNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessageResponse handleGroupNotFoundException(GroupNotFoundException ex, WebRequest request){

        return new ErrorMessageResponse(
            HttpStatus.NOT_FOUND.value(),
            new Date(),
            "Group not found",
            request.getDescription(false)
        );
    }

    @ExceptionHandler(value = JournalNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessageResponse handleJournalNotFoundException(JournalNotFoundException ex, WebRequest request){

        return new ErrorMessageResponse(
            HttpStatus.NOT_FOUND.value(),
            new Date(),
            "Journal not found",
            request.getDescription(false)
        );
    }

    @ExceptionHandler(value = MarkNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessageResponse handleMarkNot(MarkNotFoundException ex, WebRequest request){

        return new ErrorMessageResponse(
            HttpStatus.NOT_FOUND.value(),
            new Date(),
           "Mark not found ",
            request.getDescription(false)
        );
    }

    @ExceptionHandler(value = StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessageResponse handleStudentNot(StudentNotFoundException ex, WebRequest request){

        return new ErrorMessageResponse(
            HttpStatus.NOT_FOUND.value(),
            new Date(),
            "Student not found",
            request.getDescription(false)
        );
    }

    @ExceptionHandler(value = SubjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessageResponse handleSubjectNot(SubjectNotFoundException ex, WebRequest request){

        return new ErrorMessageResponse(
            HttpStatus.NOT_FOUND.value(),
            new Date(),
            "Subject not found",
            request.getDescription(false)
        );
    }

    @ExceptionHandler(value = UniversityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessageResponse handleUniversityNot(UniversityNotFoundException ex, WebRequest request){

        return new ErrorMessageResponse(
            HttpStatus.NOT_FOUND.value(),
            new Date(),
            "University not found ",
            request.getDescription(false)
        );
    }
}
