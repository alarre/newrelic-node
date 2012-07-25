import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

/**
 * Created by: Matias Rege
 */
public class Monitor  {
		
	private Monitor(int port, int buffer, boolean debug) throws IOException {
		NioSocketAcceptor acceptor = new NioSocketAcceptor();
		  
        acceptor.getFilterChain().addLast(
                "codec",
                new ProtocolCodecFilter(new TextLineCodecFactory(Charset
                        .forName("UTF-8"))));

		MonitorHandler handler = new MonitorHandler();
		handler.setDebug(debug);
		
        acceptor.setHandler(handler);
        acceptor.bind(new InetSocketAddress(port));

        System.out.println("Listening on port " + port);
        
//		ByteBuffer.setUseDirectBuffers(false);
//		ByteBuffer.setAllocator(new SimpleByteBufferAllocator());
//
//		System.out.println(Runtime.getRuntime().availableProcessors());
//		SocketAcceptor acceptor = new SocketAcceptor(Runtime.getRuntime().availableProcessors() + 1, Executors.newCachedThreadPool());
//
//		SocketAcceptorConfig cfg = new SocketAcceptorConfig();
//		TextLineCodecFactory factory = new TextLineCodecFactory(Charset.forName("UTF-8"));
//		factory.setDecoderMaxLineLength(buffer);
//		cfg.getFilterChain().addLast("codec", new ProtocolCodecFilter(factory));
//		MonitorHandler handler = new MonitorHandler();
//		handler.setDebug(debug);
//		acceptor.bind(new InetSocketAddress(port), handler, cfg);
	}

	public static void main(String[] args) throws IOException {
		System.out.println("Iniciando monitor con parametros: "+Arrays.toString(args));
		if (args.length > 2)
			new Monitor(Integer.valueOf(args[0]), Integer.valueOf(args[1]), Boolean.valueOf(args[2]));
		else
			new Monitor(Integer.valueOf(args[0]), Integer.valueOf(args[1]), false);
		System.out.println("Server started.");
	}
}

