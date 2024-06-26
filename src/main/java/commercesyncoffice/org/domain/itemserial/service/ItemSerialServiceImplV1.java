package commercesyncoffice.org.domain.itemserial.service;

import commercesyncoffice.org.domain.item.Item;
import commercesyncoffice.org.domain.item.service.ItemService;
import commercesyncoffice.org.domain.itemserial.ItemSerial;
import commercesyncoffice.org.domain.itemserial.dto.ItemSerialCreateDto;
import commercesyncoffice.org.domain.itemserial.repository.ItemSerialRepository;
import commercesyncoffice.org.global.exception.CustomException;
import commercesyncoffice.org.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Primary
@Service
@RequiredArgsConstructor
public class ItemSerialServiceImplV1 implements ItemSerialService {

    private final ItemSerialRepository itemSerialRepository;
    private final ItemService itemService;

    @Override
    @Transactional
    public void createItemSerial(Long itemId, ItemSerialCreateDto itemSerialCreateDto) {

        Item item = itemService.getItemById(itemId);

        if (itemSerialRepository.checkSameSerialInItem(itemSerialCreateDto.serial(),
                item.getId())) {
            throw new CustomException(ExceptionCode.SAME_SERIAL_IN_ITEM);
        }

        itemSerialRepository.save(ItemSerial.createItemSerial(itemSerialCreateDto, item));
    }
}
