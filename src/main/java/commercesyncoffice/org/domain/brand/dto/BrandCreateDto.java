package commercesyncoffice.org.domain.brand.dto;

//TODO 추후 domain 에 따라 validation 추가
public record BrandCreateDto(
        String name,
        String description
) {

}
