package commercesyncoffice.org.domain.category.repository;

import commercesyncoffice.org.domain.category.model.Category;
import commercesyncoffice.org.domain.category.dto.response.GetCategoryListDto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT"
            + " new commercesyncoffice.org.domain.category.dto.GetCategoryListDto(c.name)"
            + " from Category c"
            + " where c.brand.id = :brandId"
    )
    List<GetCategoryListDto> findCategoryListByBrand(@Param("brandId") Long brandId);

    Optional<Category> findByIdAndBrandId(Long categoryId, Long brandId);
}
