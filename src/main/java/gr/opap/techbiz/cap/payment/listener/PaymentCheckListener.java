package gr.opap.techbiz.cap.payment.listener;

import gr.opap.techbiz.cap.payment.repository.PaymentRepository;
import gr.opap.techbiz.cap.payment.rest.PaymentRESTService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentCheckListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentCheckListener.class);


    private final PaymentRepository paymentRepository;

    public PaymentCheckListener(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }


    @RabbitListener(queues = {"successCheck"})
    public void successPayment(PaymentRESTService.PaymentRequestDTO dto) {
        LOGGER.info("Payment passed the AF check, saving! {}", dto);
        paymentRepository.save(dto.toPayment());
    }

    @RabbitListener(queues = {"failureCheck"})
    public void failurePayment(PaymentRESTService.PaymentRequestDTO dto) {
        LOGGER.info("Payment did not pass the AF check, must email {} {}", dto, dto.userId());

    }


}
