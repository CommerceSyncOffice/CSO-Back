package commercesyncoffice.org.domain.category.dto;

//TODO 추후 domain 에 따라 validation 추가
public record CategoryCreateDto(
        String name,
        String description
) {

}
