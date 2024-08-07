package commercesyncoffice.org.domain.item.dto.request;


public record ItemCreateDto(
        String name,

        Integer originPrice,

        Integer price,

        String description,

        String img,

        String barcode,

        Boolean isSerial,

        Long categoryId
) {

}
