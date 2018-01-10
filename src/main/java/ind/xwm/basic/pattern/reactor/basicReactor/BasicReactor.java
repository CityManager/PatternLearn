package ind.xwm.basic.pattern.reactor.basicReactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Callable;

/**
 * 为优化 BeforeReactor 中的 阻塞且多线程的问题，使用Reactor模式
 * <p>
 * 1. 利用NIO，解决阻塞问题
 * 2. 利用 事件派发，单线程处理网络请求
 * <p>
 * 处理步骤：
 * 1. 派发器 开启 ServerSocketChannel ，并注册到 Selector 上
 * 2. 启动派发器的循环派发工作：
 * 3. 从 Selector 中获取出发了事件的 SelectionKey
 * 4. 从 SelectionKey 中获取 handler， 并调用handler
 * <p>
 * 注意 handler 中有一个为 Acceptor 用于处理accept事件，并将 SocketChannel 注册到 Selector 上
 */
public class BasicReactor {
    public static void main(String[] args) throws IOException {
        new Thread(new Dispatcher()).start();
    }
}

class Dispatcher implements Runnable {
    Selector selector;

    public Dispatcher() throws IOException {
        this.selector = Selector.open();
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.socket().bind(new InetSocketAddress(5566));
        channel.configureBlocking(false);
        SelectionKey sk = channel.register(selector, SelectionKey.OP_ACCEPT);
        sk.attach(new Acceptor(sk));
    }

    public void dispatch(SelectionKey sk) throws Exception { // Accepter 和 Handler 都会在这里被执行
        Callable callable = (Callable) sk.attachment();
        if (callable != null) {
            callable.call();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                selector.select();
                Set<SelectionKey> keySet = selector.selectedKeys();
                Iterator<SelectionKey> it = keySet.iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    dispatch(key);
                    it.remove();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

class Acceptor implements Callable {
    SelectionKey selectionKey;

    public Acceptor(SelectionKey selectionKey) {
        this.selectionKey = selectionKey;
    }

    @Override
    public Object call() throws Exception {
        SocketChannel channel = ((ServerSocketChannel) selectionKey.channel()).accept();
        new ReadHandler(selectionKey.selector(), channel);
        return null;
    }
}

class ReadHandler implements Callable {
    SelectionKey selectionKey;
    SocketChannel socketChannel;

    public ReadHandler(Selector selector, SocketChannel socketChannel) throws IOException {
        socketChannel.configureBlocking(false);
        this.socketChannel = socketChannel;
        selectionKey = socketChannel.register(selector, SelectionKey.OP_READ);
        selectionKey.attach(this);
        selector.wakeup();
    }

    @Override
    public Object call() throws Exception {
        // read data from the channel

        // if need writing data to the channel
        new WriteHandler(selectionKey, socketChannel);

        return null;
    }
}

class WriteHandler implements Callable {
    SelectionKey selectionKey;
    SocketChannel socketChannel;

    public WriteHandler(SelectionKey selectionKey, SocketChannel socketChannel) {
        this.selectionKey = selectionKey;
        this.socketChannel = socketChannel;
        selectionKey.interestOps(SelectionKey.OP_WRITE);
        selectionKey.attach(this);
    }

    @Override
    public Object call() throws Exception {
        // writing data to the channel
        // if finish
        socketChannel.close();
        return null;
    }
}