package DTO;

import DTO.AbstractDTO.AbstractMenu;

public class MenuBeverageDTO extends AbstractMenu {
    public MenuBeverageDTO() {
    }

    public MenuBeverageDTO(int daysinweek, Integer id_product, int quantity) {
        super(daysinweek, id_product, quantity);
    }
}
