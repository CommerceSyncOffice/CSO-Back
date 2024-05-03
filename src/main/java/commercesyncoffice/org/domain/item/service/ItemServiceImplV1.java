package commercesyncoffice.org.domain.item.service;

import commercesyncoffice.org.domain.brand.Brand;
import commercesyncoffice.org.domain.brand.service.BrandService;
import commercesyncoffice.org.domain.category.Category;
import commercesyncoffice.org.domain.category.service.CategoryService;
import commercesyncoffice.org.domain.item.Item;
import commercesyncoffice.org.domain.item.dto.ItemChangeCategoryDto;
import commercesyncoffice.org.domain.item.dto.ItemCreateDto;
import commercesyncoffice.org.domain.item.dto.ItemDetailDto;
import commercesyncoffice.org.domain.item.repository.ItemRepository;
import commercesyncoffice.org.global.exception.CustomException;
import commercesyncoffice.org.global.exception.ExceptionCode;
import jakarta.transaction.Transactional;
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
    @Transactional
    public Long createItem(ItemCreateDto itemCreateDto, Long brandId) {

        Category category = null;

        Brand brand = brandService.getBrandById(brandId);

        if (itemCreateDto.categoryId() != null) {
            category = categoryService.getCategoryByIdAndBrandId(itemCreateDto.categoryId(), brandId);
        }

        if (itemRepository.checkSameBarcodeInBrand(itemCreateDto.barcode(), brandId)) {
            throw new IllegalArgumentException("브랜드 내에 동일한 바코드가 있습니다.");
        }

        return itemRepository.save(Item.createItem(itemCreateDto, category, brand)).getId();
    }

    @Override
    public ItemDetailDto getItem(Long itemId) {

        return itemRepository.findByIdCustom(itemId).orElseThrow(
                () -> new CustomException(ExceptionCode.NOT_FOUND_ITEM)
        );
    }

    @Override
    @Transactional
    public void changeItemIsSerial(Long itemId) {

        Item item = getItemById(itemId);

        if (item.getIsSerial() && itemRepository.isHavingSerial(itemId)) {
            throw new IllegalArgumentException("이 아이템에 등록 된 시리얼 번호를 지우고 다시 시도해주세요");
        }

        item.changeIsSerial();
    }

    @Override
    @Transactional
    public void changeItemCategory(Long itemId, ItemChangeCategoryDto itemChangeCategoryDto) {

        Category category = null;

        Item item = getItemById(itemId);

        if (itemChangeCategoryDto.categoryId() != null) {
            category = categoryService.getCategoryByIdAndBrandId(itemChangeCategoryDto.categoryId(), item.getBrandId());
        }

        item.changeCategory(category);
    }

    @Override
    public Item getItemById(Long itemId) {

        // TODO N+1 문제 해결
        return itemRepository.findById(itemId).orElseThrow(
                () -> new CustomException(ExceptionCode.NOT_FOUND_ITEM)
        );
    }
}
