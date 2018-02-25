package ind.xwm.basic.pattern.producerCustomer.awaitSignal;

import ind.xwm.basic.pattern.producerCustomer.Storage;
import ind.xwm.basic.pattern.producerCustomer.waitNotify.Phone;
import ind.xwm.basic.pattern.producerCustomer.waitNotify.PhoneProducer;
import ind.xwm.basic.pattern.producerCustomer.waitNotify.PhoneSalesman;

public class AwaitSignalMain {
    public static void main(String[] args) {
        Storage<Phone> storage = new ReentrantPhoneStorage<>();
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
