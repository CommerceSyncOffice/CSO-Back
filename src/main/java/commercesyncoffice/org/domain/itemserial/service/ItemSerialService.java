package commercesyncoffice.org.domain.itemserial.service;

import commercesyncoffice.org.domain.itemserial.dto.request.ItemSerialCreateDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface ItemSerialService {

    void createItemSerial(UserDetails userDetails, Long itemId, ItemSerialCreateDto itemSerialCreateDto);
}
