package ind.xwm.basic.pattern.creatorCustomer.awaitSignal;

import ind.xwm.basic.pattern.creatorCustomer.Storage;
import ind.xwm.basic.pattern.creatorCustomer.waitNotify.Phone;
import ind.xwm.basic.pattern.creatorCustomer.waitNotify.PhoneCreator;
import ind.xwm.basic.pattern.creatorCustomer.waitNotify.PhoneSalesman;

public class AwaitSignalMain {
    public static void main(String[] args) {
        Storage<Phone> storage = new ReentrantPhoneStorage<Phone>();
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new PhoneCreator(storage));
            thread.start();
        }

        for(int i=0;i<5;i++) {
            Thread thread = new Thread(new PhoneSalesman(storage));
            thread.start();
        }
    }
}
