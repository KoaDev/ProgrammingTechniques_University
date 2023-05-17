package model;

/**
 * @param id        int
 * @param clientId int
 * @param productId int
 * @param quantity int
 * @param totalPrice double
 *                   Bill class
 */
public record Bill(int id, int clientId, int productId, int quantity, double totalPrice) {

}