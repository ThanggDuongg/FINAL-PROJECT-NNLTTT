package DTO;

import DTO.AbstractDTO.AbstractDetailOrder;

public class DetailFoodOrderDTO extends AbstractDetailOrder {
    public DetailFoodOrderDTO() {
    }

    public DetailFoodOrderDTO(Integer idOrder, Integer idProduct, int quantity) {
        super(idOrder, idProduct, quantity);
    }

    public DetailFoodOrderDTO(Integer id, Integer idOrder, Integer idProduct, int quantity) {
        super(id, idOrder, idProduct, quantity);
    }
}
