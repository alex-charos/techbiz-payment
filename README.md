# Payments service for TechBiz demo

GET /payments/${userId} 
will return a user's payments

POST /payments
{
    "paymentType": ${paymentType},
    "amountInCents": ${amount},
    "userId":"${userId}"
}

will create a payment
