package com.example.simpleaccountant.data.model;

public class Customer {
    private long id; private String fullName; private String phone; private String email; private String address; private String note; private String createdAt; private String updatedAt;
    public long getId() { return id; } public void setId(long id) { this.id = id; }
    public String getFullName() { return fullName; } public void setFullName(String fullName) { this.fullName = fullName; }
    public String getPhone() { return phone; } public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; } public void setEmail(String email) { this.email = email; }
    public String getAddress() { return address; } public void setAddress(String address) { this.address = address; }
    public String getNote() { return note; } public void setNote(String note) { this.note = note; }
    public String getCreatedAt() { return createdAt; } public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    public String getUpdatedAt() { return updatedAt; } public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
}
