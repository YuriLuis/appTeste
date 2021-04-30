package com.app_empresas.model;

public class Enterprise {

    private int id;
    private String enterpriseName;
    private String description;
    private String enterprise_type;
    private boolean own_enterprise;
    private String city;
    private String photo;

    public Enterprise(int id, String enterpriseName, String description, String enterprise_type, boolean own_enterprise,
                      String city, String photo) {
        this.id = id;
        this.enterpriseName = enterpriseName;
        this.description = description;
        this.enterprise_type = enterprise_type;
        this.own_enterprise = own_enterprise;
        this.city = city;
        this.photo = "https://empresas.ioasys.com.br" +photo;
    }

    public int getId() {
        return id;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public String getDescription() {
        return description;
    }

    public String getEnterprise_type() {
        return enterprise_type;
    }

    public boolean isOwn_enterprise() {
        return own_enterprise;
    }

    public String getCity() {
        return city;
    }

    public String getPhoto() {
        return photo;
    }
}
