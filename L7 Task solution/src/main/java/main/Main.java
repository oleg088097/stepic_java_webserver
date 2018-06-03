package main;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class Main {
    private static final String BUE = "Bue.";
    public static void main(String[] args) throws Exception {
        Selector selector = Selector.open();

        ServerSocketChannel serverSocket = ServerSocketChannel.open().bind(new InetSocketAddress(5050));
        serverSocket.configureBlocking(false);
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);

        ByteBuffer buffer = ByteBuffer.allocate(1024*8);

        System.out.println("Server started");

        while (true){
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey next = iterator.next();
                if (next.isAcceptable()) {
                    SocketChannel client = serverSocket.accept();
                    client.configureBlocking(false);
                    client.register(selector, SelectionKey.OP_READ);
                }

                if (next.isReadable()) {
                    SocketChannel channel = (SocketChannel)next.channel();
                    channel.read(buffer);
                    buffer.flip();
                    String input = new String(buffer.array()).trim();
                    channel.write(buffer);
                    if (input.equals(BUE)){
                        channel.close();
                    }
                    buffer.clear();
                }

                iterator.remove();
            }
        }
    }
}
