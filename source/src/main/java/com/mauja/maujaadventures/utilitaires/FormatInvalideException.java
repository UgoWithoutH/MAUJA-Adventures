package com.mauja.maujaadventures.utilitaires;

import java.io.IOException;

public class FormatInvalideException extends IOException {
    public FormatInvalideException() {}

    public FormatInvalideException(String message) {
        super(message);
    }

    public FormatInvalideException(String message, Throwable cause) {
        super(message, cause);
    }

    public FormatInvalideException(Throwable cause) {
        super(cause);
    }
}
