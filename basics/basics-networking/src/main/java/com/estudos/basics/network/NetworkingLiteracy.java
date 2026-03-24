package com.estudos.basics.network;

import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

/**
 * PT: URI, host local e portas — sem depender de serviço remoto acessível. Teoria nos {@code .md} do pacote.
 * EN: URI, local host, and ports — without relying on a reachable remote service. Theory in the package {@code .md} files.
 */
public final class NetworkingLiteracy {

    private NetworkingLiteracy() {
    }

    static void meuPlayground() {
    }

    public static void main(String[] args) throws URISyntaxException, UnknownHostException {
        URI api = new URI("https://api.exemplo.com/v1/recursos?q=1");
        System.out.println("URI: " + api);
        System.out.println("scheme=" + api.getScheme() + " host=" + api.getHost() + " port=" + api.getPort()
                + " path=" + api.getPath() + " query=" + api.getQuery());

        InetAddress local = InetAddress.getLocalHost();
        System.out.println("PT: host local: " + local.getHostName() + " / " + local.getHostAddress());
        System.out.println("EN: localhost: " + local.getHostName() + " / " + local.getHostAddress());

        // meuPlayground();
    }
}
