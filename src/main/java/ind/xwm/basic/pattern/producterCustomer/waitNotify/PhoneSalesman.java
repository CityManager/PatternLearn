package ind.xwm.basic.pattern.producterCustomer.waitNotify;

public class PhoneSalesman implements Runnable {
    private PhoneStorage<Phone> storage;

    public PhoneSalesman(PhoneStorage<Phone> storage) {
        this.storage = storage;
    }

    public void run() {
        while(true) {
            salePhone();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void salePhone() {
        long threadId = Thread.currentThread().getId();
        try {
            Phone phone = storage.takeOut();
            System.out.println("线程" + threadId + ":成功销售产品" + phone.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
