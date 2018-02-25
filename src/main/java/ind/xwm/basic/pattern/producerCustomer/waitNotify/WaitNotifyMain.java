package ind.xwm.basic.pattern.producerCustomer.waitNotify;

import ind.xwm.basic.pattern.producerCustomer.Storage;

public class WaitNotifyMain {
    public static void main(String[] args) {
        Storage<Phone> storage = new PhoneStorage<Phone>();
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new PhoneProducer(storage));
            thread.start();
        }

        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new PhoneSalesman(storage));
            thread.start();
        }
    }
}
