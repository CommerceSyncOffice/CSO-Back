package commercesyncoffice.org.domain.brand.model;

public record BrandId(Long id) {
    public static BrandId from(Long id) {
        return new BrandId(id);
    }
}
