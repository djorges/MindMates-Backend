package MindMates.NoCountry.user;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

/**
 * Project: Backend
 * Author: djorges
 * Date: 18/9/2024
 */

public class UserResponse {
    private String id;
    private String nombre;
    private String correo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public UserResponse(String id, String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
        this.id = id;
    }
}
