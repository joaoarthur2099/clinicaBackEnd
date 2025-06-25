package br.com.clinicamedica.Clinica.dto;

import java.util.Objects;

public final class RegisterDTO {
    private final String fullName;
    private final String userName;
    private final String password;

    public RegisterDTO(String fullName, String userName, String password) {
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
    }

    public String fullName() {
        return fullName;
    }

    public String userName() {
        return userName;
    }

    public String password() {
        return password;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (RegisterDTO) obj;
        return Objects.equals(this.fullName, that.fullName) &&
                Objects.equals(this.userName, that.userName) &&
                Objects.equals(this.password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, userName, password);
    }

    @Override
    public String toString() {
        return "RegisterDTO[" +
                "fullName=" + fullName + ", " +
                "userName=" + userName + ", " +
                "password=" + password + ']';
    }

}
