package dev.shanelucy.proxy.api;

/** A {@link FunctionalInterface} which consumers should implement to proxy requests. */
@FunctionalInterface
public interface Proxy {

  /** This method handles proxying requests */
  void proxyRequest();
}
