package com.example.biketrack;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Session {
    private static String ciclistaActual;

    public static void setCiclistaActual(String id_ciclista) {
        ciclistaActual = id_ciclista;
    }

    public static String getCiclistaActual() {
        return ciclistaActual;
    }

    public static boolean isUsuarioAutenticado() {
        return ciclistaActual != null && !ciclistaActual.isEmpty();
    }
    public static void cerrarSesion() {
        ciclistaActual = null;
    }

    private static final Logger logger = Logger.getLogger(UsuarioService.class.getName());

    // Método para obtener el DNI del usuario actual
    public String getCurrentUserDNI() {
        String idCiclista = UsuarioSesion.getDniActual(); // Obtener desde la sesión o contexto

        if (dniUsuario == null || dniUsuario.trim().isEmpty()) {
            logger.log(Level.WARNING, "⚠ No hay un usuario autenticado.");
            return null;
        }

        return dniUsuario;

    }
}