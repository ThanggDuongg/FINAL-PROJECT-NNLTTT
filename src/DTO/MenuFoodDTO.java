package DTO;

import DTO.AbstractDTO.AbstractMenu;

public class MenuFoodDTO extends AbstractMenu {
    public MenuFoodDTO() {
    }

    public MenuFoodDTO(int daysinweek, Integer id_product, int quantity) {
        super(daysinweek, id_product, quantity);
    }
}
