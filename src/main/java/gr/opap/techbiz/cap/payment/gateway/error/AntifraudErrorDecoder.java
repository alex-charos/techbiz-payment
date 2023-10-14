package gr.opap.techbiz.cap.payment.gateway.error;

import feign.Response;
import feign.codec.ErrorDecoder;
import gr.opap.techbiz.cap.payment.exception.IllegalPaymentException;
import org.springframework.stereotype.Component;

@Component
public class AntifraudErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {

        switch (response.status()) {
            case 403:
                return new IllegalPaymentException();
            default:
                return errorDecoder.decode(methodKey, response);
        }
    }
}