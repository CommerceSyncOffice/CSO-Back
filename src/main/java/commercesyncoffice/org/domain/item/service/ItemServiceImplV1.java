package commercesyncoffice.org.domain.item.service;

import commercesyncoffice.org.domain.brand.service.BrandService;
import commercesyncoffice.org.domain.category.Category;
import commercesyncoffice.org.domain.category.service.CategoryService;
import commercesyncoffice.org.domain.item.Item;
import commercesyncoffice.org.domain.item.dto.ItemCreateDto;
import commercesyncoffice.org.domain.item.dto.ItemDetailDto;
import commercesyncoffice.org.domain.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Primary
public class ItemServiceImplV1 implements ItemService {

    private final ItemRepository itemRepository;
    private final BrandService brandService;
    private final CategoryService categoryService;

    @Override
    public Long createItem(ItemCreateDto itemCreateDto, Long brandId) {

        Category category = null;

        brandService.checkBrand(brandId);

        if (itemCreateDto.categoryId() != null) {
            category = categoryService.getCategoryByIdAndBrandId(itemCreateDto.categoryId(), brandId);
        }

        return itemRepository.save(Item.createItem(itemCreateDto, category)).getId();
    }

    @Override
    public ItemDetailDto getItem(Long itemId) {

        return itemRepository.findByIdCustom(itemId).orElseThrow(
                () -> new IllegalArgumentException()
        );
    }
}
