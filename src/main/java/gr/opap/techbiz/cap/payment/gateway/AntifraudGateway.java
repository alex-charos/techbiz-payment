package gr.opap.techbiz.cap.payment.gateway;

import gr.opap.techbiz.cap.payment.rest.PaymentRESTService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "antifraud", url = "${feign.antifraud.url}")
public interface AntifraudGateway {

    @RequestMapping(method = RequestMethod.POST, value = "/antifraud", consumes = "application/json")
    void checkPayment(@RequestBody PaymentRESTService.PaymentRequestDTO dto) ;

}
