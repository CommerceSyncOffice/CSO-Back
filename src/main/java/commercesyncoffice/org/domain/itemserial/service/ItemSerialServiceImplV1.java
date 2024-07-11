package commercesyncoffice.org.domain.itemserial.service;

import commercesyncoffice.org.domain.brand.service.BrandService;
import commercesyncoffice.org.domain.item.model.Item;
import commercesyncoffice.org.domain.item.service.ItemService;
import commercesyncoffice.org.domain.itemserial.exception.ItemSerialException;
import commercesyncoffice.org.domain.itemserial.message.ExceptionCode;
import commercesyncoffice.org.domain.itemserial.model.ItemSerial;
import commercesyncoffice.org.domain.itemserial.dto.request.ItemSerialCreateDto;
import commercesyncoffice.org.domain.itemserial.repository.ItemSerialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Primary
@Service
@RequiredArgsConstructor
public class ItemSerialServiceImplV1 implements ItemSerialService {

    private final ItemSerialRepository itemSerialRepository;
    private final ItemService itemService;
    private final BrandService brandService;

    @Override
    @Transactional
    public void createItemSerial(UserDetails userDetails, Long itemId, ItemSerialCreateDto itemSerialCreateDto) {

        Item item = itemService.getItemWithBrandByItemId(itemId);

        brandService.validateBrand(userDetails, item.getBrandId());

        if (itemSerialRepository.checkSameSerialInItem(itemSerialCreateDto.serial(), item.getId())) {
            throw new ItemSerialException(ExceptionCode.SAME_SERIAL_IN_ITEM);
        }

        itemSerialRepository.save(ItemSerial.createItemSerial(itemSerialCreateDto, item));
    }
}
