package jp.co.practice.error;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class CustomErrorController implements ErrorController {

    @RequestMapping
    public String error(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        if (status == HttpStatus.NOT_FOUND){
            return "error/404";
        }
        return "error/error";
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Object statusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (statusCode != null && statusCode.toString().equals("404")) {
            return HttpStatus.NOT_FOUND;
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
