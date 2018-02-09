package ind.xwm.basic.pattern.producterCustomer.waitNotify;

public class PhoneMain {
    public static void main(String[] args) {
        PhoneStorage<Phone> storage = new PhoneStorage<Phone>();
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new PhoneProducter(storage));
            thread.start();
        }

        for(int i=0;i<5;i++) {
            Thread thread = new Thread(new PhoneSaler(storage));
            thread.start();
        }
    }
}
