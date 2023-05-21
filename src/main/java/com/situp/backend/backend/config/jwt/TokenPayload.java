package com.situp.backend.backend.config.jwt;

import java.util.Date;

public record TokenPayload(Long id, Date lastConnection, boolean isAdmin) { }