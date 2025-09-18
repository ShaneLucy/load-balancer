package dev.shanelucy.server.model;

import dev.shanelucy.server.api.Server;
import java.util.UUID;

public record MockServer(String host, int port, UUID id) implements Server {}
