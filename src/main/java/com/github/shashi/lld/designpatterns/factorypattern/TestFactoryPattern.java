package com.github.shashi.lld.designpatterns.factorypattern;

public class TestFactoryPattern {
    public static void main(String[] args) {
        Payment upiPayment = PaymentFactory.getPaymentMethod("UPI");
        upiPayment.pay(100);
    }
}
interface Payment{
    void pay(double amount);
}
class CreditCard implements Payment{

    @Override
    public void pay(double amount) {
        System.out.println("made payment through credit card");
    }
}
class UPI implements Payment{

    @Override
    public void pay(double amount) {
        System.out.println("made payment through upi");
    }
}
class PaymentFactory{
    public static Payment getPaymentMethod(String method){
        if("CREDIT_CARD".equals(method)){
            return new CreditCard();
        }else if("UPI".equals(method)){
            return new UPI();
        }else return null;
    }
}
