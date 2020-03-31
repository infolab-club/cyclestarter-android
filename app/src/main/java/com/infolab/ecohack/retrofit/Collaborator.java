package com.infolab.ecohack.retrofit;

import com.google.gson.annotations.SerializedName;

/**
 * Класс сотрудника офиса.
 * @author Глеб Новиков
 */
public class Collaborator {
    // TODO: Изменить название "name" на "full_name".
    @SerializedName("name")
    private String fullName;

    @SerializedName("email")
    private String email;

    // TODO: Изменить название "phone" на "phone_number".
    @SerializedName("phone")
    private String phoneNumber;

    @SerializedName("address")
    private String address;

    // TODO: Изменить название "room" на "office".
    @SerializedName("room")
    private String office;

    public Collaborator() {}

    public Collaborator(String fullName, String email, String phoneNumber, String address,
                        String office) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.office = office;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        if (phoneNumber == null) return "";
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }
}
