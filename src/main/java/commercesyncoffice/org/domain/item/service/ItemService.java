package commercesyncoffice.org.domain.item.service;

import commercesyncoffice.org.domain.item.Item;
import commercesyncoffice.org.domain.item.dto.ItemChangeCategoryDto;
import commercesyncoffice.org.domain.item.dto.ItemCreateDto;
import commercesyncoffice.org.domain.item.dto.ItemDetailDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface ItemService {

    Long createItem(UserDetails userDetails, ItemCreateDto itemCreateDto, Long brandId);

    ItemDetailDto getItem(UserDetails userDetails, Long itemId);

    void changeItemIsSerial(UserDetails userDetails, Long itemId);

    void changeItemCategory(UserDetails userDetails, Long itemId, ItemChangeCategoryDto itemChangeCategoryDto);

    Item getItemById(Long itemId);

    Item getItemWithBrandByItemId(Long itemId);
}
