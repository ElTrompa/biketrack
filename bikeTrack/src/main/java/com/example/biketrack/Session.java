package com.example.biketrack;

import java.util.logging.Logger;
import java.util.logging.Level;

public class Session {
    private static String usuarioActual;

    private static final Logger logger = Logger.getLogger(Session.class.getName());

    public static String getUsuarioActual() {
        return usuarioActual;
    }

    public static void setUsuarioActual(String usuarioActual) {
        Session.usuarioActual = usuarioActual;
    }

    public static boolean isUsuarioAutenticado() {
        return usuarioActual != null && !usuarioActual.isEmpty();
    }

    public static void cerrarSesion() {
        usuarioActual = null;
    }

    public static String getCurrentUserDNI() {
        if (!isUsuarioAutenticado()) {
            logger.log(Level.WARNING, "⚠ No hay un usuario autenticado.");
            return null;
        }
        return usuarioActual;
    }
}