package br.com.clinicamedica.Clinica.model;

public enum RoleModel {
    ADMIN("admin"), USER("user");
    private String name;
    RoleModel(String name){
        this.name = name;
    }
}