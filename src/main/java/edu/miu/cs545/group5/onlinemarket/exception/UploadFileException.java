package edu.miu.cs545.group5.onlinemarket.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED, reason = "Unable to upload image file!")
public class UploadFileException extends RuntimeException{

    private String student_image_saving_failed;
    public UploadFileException(String student_image_saving_failed) {
        this.student_image_saving_failed = student_image_saving_failed;
    }
}
