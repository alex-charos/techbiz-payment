package gr.opap.techbiz.cap.payment.entity;

import jakarta.persistence.*;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "payments_sequence")
    @SequenceGenerator(name = "payments_sequence",
        sequenceName = "payments_sequence",
        initialValue = 1, allocationSize = 1)
    @Column(name="id")
    private Long id;
    @Column(name="paymenttype")
    private String paymentType;

    @Column(name="amountincents")
    private Integer amountInCents;

    @Column(name="userid")
    private String userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getAmountInCents() {
        return amountInCents;
    }

    public void setAmountInCents(Integer amountInCents) {
        this.amountInCents = amountInCents;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Payment{" +
            "id=" + id +
            ", paymentType='" + paymentType + '\'' +
            ", amountInCents=" + amountInCents +
            ", userId='" + userId + '\'' +
            '}';
    }
}
