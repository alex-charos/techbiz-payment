package gr.opap.techbiz.cap.payment.repository;


import gr.opap.techbiz.cap.payment.entity.Payment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PaymentRepository extends CrudRepository<Payment, Long> {

    List<Payment> findByUserId(String userId);
}
