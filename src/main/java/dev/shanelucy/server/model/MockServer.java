package dev.shanelucy.model;

import dev.shanelucy.server.api.Server;

public record MockServer(String host, int port) implements Server {}
