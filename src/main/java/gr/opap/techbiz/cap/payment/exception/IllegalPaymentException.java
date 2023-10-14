package gr.opap.techbiz.cap.payment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.FORBIDDEN, reason="Payment cannot proceed")
public class IllegalPaymentException extends Exception {
}
