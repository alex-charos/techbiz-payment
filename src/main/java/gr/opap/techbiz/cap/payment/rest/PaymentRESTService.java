package gr.opap.techbiz.cap.payment.rest;

import gr.opap.techbiz.cap.payment.entity.Payment;
import gr.opap.techbiz.cap.payment.gateway.AntifraudGateway;
import gr.opap.techbiz.cap.payment.repository.PaymentRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/payments")
public class PaymentRESTService {

    private final PaymentRepository paymentRepository;
    private final RabbitTemplate rabbitTemplate;

    public PaymentRESTService(PaymentRepository paymentRepository,RabbitTemplate rabbitTemplate) {
        this.paymentRepository = paymentRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping
    @RequestMapping("/{userId}")
    public List<PaymentDTO> getByUserId( @PathVariable("userId") String userId) {
        List<Payment> userPayments = paymentRepository.findByUserId(userId);

        List<PaymentDTO> dtos = userPayments.stream().map(PaymentDTO::fromPayment).collect(Collectors.toList());

        return dtos;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void createPayment(@RequestBody PaymentRequestDTO requestDTO) {
       rabbitTemplate.convertAndSend("paymentCheckExchange", "payment.check", requestDTO);
    }


    public record PaymentDTO(Long id, String paymentType, Integer amountInCents, String userId){
        public static PaymentDTO fromPayment(Payment p ) {
            return new PaymentDTO(p.getId(), p.getPaymentType(), p.getAmountInCents(), p.getUserId());
        }
    }

    public record PaymentRequestDTO(String paymentType, Integer amountInCents, String userId){
        public Payment toPayment(){
            Payment p = new Payment();
            p.setPaymentType(paymentType);
            p.setAmountInCents(amountInCents);
            p.setUserId(userId);
            return p;
        }
    }
}
