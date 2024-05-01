package commercesyncoffice.org.domain.item.service;

import commercesyncoffice.org.domain.item.dto.ItemChangeCategoryDto;
import commercesyncoffice.org.domain.item.dto.ItemCreateDto;
import commercesyncoffice.org.domain.item.dto.ItemDetailDto;

public interface ItemService {

    Long createItem(ItemCreateDto itemCreateDto, Long brandId);

    ItemDetailDto getItem(Long itemId);

    void changeItemIsSerial(Long itemId);

    void changeItemCategory(Long itemId, ItemChangeCategoryDto itemChangeCategoryDto);
}
