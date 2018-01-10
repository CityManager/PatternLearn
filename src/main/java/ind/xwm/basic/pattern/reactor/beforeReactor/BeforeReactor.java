package ind.xwm.basic.pattern.reactor.beforeReactor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 以网络编程来做例子
 * 1. 创建一个ServerSocket服务器
 * 2. accept请求后，将socket传入新建线程处理
 * <p>
 * 阻塞做法，且是多线程，负载增加时（尤其时在每次网络通信都会耗时较久的情况下）
 * 线程创建销毁，以及线程间切换会大量浪费系统资源
 * <p>
 * server导致阻塞的原因：
 * 1、serversocket的accept方法，阻塞等待client连接，直到client连接成功。
 * 2、线程从socket inputstream读入数据，会进入阻塞状态，直到全部数据读完。
 * 3、线程向socket outputstream写入数据，会阻塞直到全部数据写完。
 * <p>
 * client导致阻塞的原因：
 * 1、client建立连接时会阻塞，直到连接成功。
 * 2、线程从socket输入流读入数据，如果没有足够数据读完会进入阻塞状态，直到有数据或者读到输入流末尾。
 * 3、线程从socket输出流写入数据，直到输出所有数据。
 * 4、socket.setsolinger()设置socket的延迟时间，当socket关闭时，会进入阻塞状态，直到全部数据都发送完或者超时。
 */
public class BeforeReactor {
    public static void main(String[] args) throws IOException {
        Accepter accpter = new Accepter();
        accpter.accept();
    }
}

class Accepter {
    ServerSocket serverSocket;

    public Accepter() {
    }

    public void accept() throws IOException {
        serverSocket = new ServerSocket(5656);
        while (true) {
            new Thread(new Handler(serverSocket.accept())).start();
        }
    }

}

class Handler implements Runnable {
    Socket socket;

    public Handler(Socket socket) {
        this.socket = socket;
    }

    private byte[] doProcess(byte[] input) {
        return String.valueOf(System.currentTimeMillis()).getBytes();
    }

    @Override
    public void run() {
        try {
            byte[] input = new byte[1023];
            socket.getInputStream().read(input);
            // do some process with input, and acquire some output data
            byte[] output = doProcess(input);
            socket.getOutputStream().write(output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
