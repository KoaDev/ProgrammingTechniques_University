package model;

public record Bill(int id, int clientId, int productId, int quantity, double totalPrice) {

}