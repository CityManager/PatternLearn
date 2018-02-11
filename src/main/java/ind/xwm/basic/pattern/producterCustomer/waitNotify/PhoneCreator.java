package ind.xwm.basic.pattern.producterCustomer.waitNotify;

public class PhoneCreator implements Runnable {
    private PhoneStorage<Phone> storage;

    public PhoneCreator(PhoneStorage<Phone> storage) {
        this.storage = storage;
    }

    public void run() {
        while (true) {
            newPhone();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void newPhone() {
        long threadId = Thread.currentThread().getId();
        try {
            for (int i = 0; i < 1; i++) {
                int id = storage.getSeries();
                Phone phone = new Phone(id);
                storage.storage(phone);
                System.out.println("线程" + threadId + ":成功生产产品" + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
