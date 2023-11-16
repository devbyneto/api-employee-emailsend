package com.example.employee.domain;

public class EmailModel {

    private String recipient;
    private String messageBody;
    private String subject;
    private TypeEmailEnum type;

    public EmailModel(String recipient, TypeEmailEnum type){

        this.recipient=recipient;
        this.type=type;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public TypeEmailEnum getType() {
        return type;
    }

    public void setType(TypeEmailEnum type) {
        this.type = type;
    }
}
