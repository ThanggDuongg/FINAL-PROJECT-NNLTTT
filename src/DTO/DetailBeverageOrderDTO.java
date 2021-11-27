package DTO;

import DTO.AbstractDTO.AbstractDetailOrder;

public class DetailBeverageOrderDTO extends AbstractDetailOrder {
    public DetailBeverageOrderDTO() {
    }

    public DetailBeverageOrderDTO(Integer idOrder, Integer idProduct, int quantity) {
        super(idOrder, idProduct, quantity);
    }

    public DetailBeverageOrderDTO(Integer id, Integer idOrder, Integer idProduct, int quantity) {
        super(id, idOrder, idProduct, quantity);
    }
}
