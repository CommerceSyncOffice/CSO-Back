package commercesyncoffice.org.domain.item.dto;

import commercesyncoffice.org.domain.itemserial.dto.ItemSerialSimpleDto;
import java.util.List;

public record ItemDetailBeforeMixDto(
        String name,

        String description,

        String category,

        String barcode,

        int originPrice,

        int price,

        Boolean isSerial,

        String img
) {

}
