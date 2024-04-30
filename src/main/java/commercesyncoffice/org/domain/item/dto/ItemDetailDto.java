package commercesyncoffice.org.domain.item.dto;

public record ItemDetailDto(
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
