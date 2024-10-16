package com.blissshop.userserver.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.NONE)
public class Endpoint {
    public static final String API = "/api";
    public static final String USER_BASE = API + "/users";
    public static final String ID = "/{id}";
}
