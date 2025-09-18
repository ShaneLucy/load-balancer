package dev.shanelucy.model;

import dev.shanelucy.client.api.Client;

public record MockClient(int queueSize, int port) implements Client {}
