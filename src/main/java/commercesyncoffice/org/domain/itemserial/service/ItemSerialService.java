package commercesyncoffice.org.domain.itemserial.service;

import commercesyncoffice.org.domain.itemserial.dto.ItemSerialCreateDto;

public interface ItemSerialService {

    void createItemSerial(Long itemId, ItemSerialCreateDto itemSerialCreateDto);
}
