package com.example.biketrack;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Session {
    private static String id_ciclista;

    public static void setId_ciclista(String dni) {
        id_ciclista = dni;
    }

    public static String getId_ciclista() {
        return id_ciclista;
    }

    public static boolean isUsuarioAutenticado() {
        return id_ciclista != null && !id_ciclista.isEmpty();
    }
    public static void cerrarSesion() {
        id_ciclista = null;
    }

    public class UsuarioService {
        private static final Logger logger = Logger.getLogger(UsuarioService.class.getName());

        // Método para obtener el DNI del usuario actual
        public String getCurrentUserDNI() {
            Session UsuarioSesion = null;
            String id_ciclistaUsuario = UsuarioSesion.getId_ciclista(); // Obtener desde la sesión o contexto

            if (id_ciclistaUsuario == null || id_ciclistaUsuario.trim().isEmpty()) {
                logger.log(Level.WARNING, "⚠ No hay un usuario autenticado.");
                return null;
            }

            return id_ciclistaUsuario;
        }
}
