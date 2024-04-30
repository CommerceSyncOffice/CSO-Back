package commercesyncoffice.org.domain.item.dto;

public record ItemDetailDto(
        String name,

        String description,

        String barcode,

        int price,

        String img
) {

}
