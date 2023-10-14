
CREATE SEQUENCE payments_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE payment
(
    id bigint,
    paymenttype  VARCHAR(255),
    amountincents int       NOT NULL,
    userid  VARCHAR(255)
);
