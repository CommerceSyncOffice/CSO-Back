package commercesyncoffice.org.domain.storeitem.dto;

public record StoreItemCreateDto (
    Long itemId,
    //TODO Valid 적용 <- Valid 핸들러 만들구
    int stock,

    int saleStock,

    int recommendStock
){

}
