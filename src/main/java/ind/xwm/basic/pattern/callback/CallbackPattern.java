package ind.xwm.basic.pattern.callback;


/**
 * Callback is a piece of executable code that is passed as an argument to other code,
 * which is expected to call back (execute) the argument at some convenient time
 * java中是抽象可回调接口，然后是调用方持有该接口，并在合适的时间调用改接口定义的方法（这个方法就是所谓的回调方法）
 */
public class CallbackPattern {
    public static void main(String[] args) {
        CallbackAble aCallBack = new CallbackProvider("可以设置回调的环境数据");

        Caller caller = new Caller();
        caller.setCallback(aCallBack);
        caller.inVokeCallbackAble();
    }
}

interface CallbackAble {
    void invoke();
}

class CallbackProvider implements CallbackAble {

    private String callbackFlag;

    CallbackProvider(String flag) {
        this.callbackFlag = flag;
    }

    public void invoke() {
        System.out.println("这是一个回调函数，" + callbackFlag);
    }
}

class Caller {
    private CallbackAble callbackAble;

    public void setCallback(CallbackAble callbackAble) {
        this.callbackAble = callbackAble;
    }

    public void inVokeCallbackAble() {
        System.out.println("开始调用回调函数");
        callbackAble.invoke();
        System.out.println("结束调用回调函数");
    }
}
