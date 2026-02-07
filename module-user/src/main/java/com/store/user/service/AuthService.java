package com.store.user.service;

public interface AuthService {

    void signup(String email, String password, String name, String role);

    String login(String email, String password);

    String refreshToken(String refreshToken);
}
