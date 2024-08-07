package commercesyncoffice.org.domain.item.dto.response;

import commercesyncoffice.org.domain.itemserial.dto.response.ItemSerialSimpleDto;
import java.util.List;

public record ItemDetailDto(
        String name,

        String description,

        String category,

        String barcode,

        int originPrice,

        int price,

        Boolean isSerial,

        String img,

        List<ItemSerialSimpleDto> serial
) {

}
