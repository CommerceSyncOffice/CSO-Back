package commercesyncoffice.org.domain.item.dto.response;

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
