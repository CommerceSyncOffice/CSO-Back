package commercesyncoffice.org.domain.item.service;

import commercesyncoffice.org.domain.brand.service.BrandService;
import commercesyncoffice.org.domain.category.Category;
import commercesyncoffice.org.domain.category.service.CategoryService;
import commercesyncoffice.org.domain.item.Item;
import commercesyncoffice.org.domain.item.dto.ItemCreateDto;
import commercesyncoffice.org.domain.item.dto.ItemDetailDto;
import commercesyncoffice.org.domain.item.repository.ItemRepository;
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
    public Long createItem(ItemCreateDto itemCreateDto, Long brandId) {

        Category category = null;

        brandService.checkBrand(brandId);

        if (itemCreateDto.categoryId() != null) {
            category = categoryService.getCategoryByIdAndBrandId(itemCreateDto.categoryId(), brandId);
        }

        if (itemRepository.checkSameBarcodeInBrand(itemCreateDto.barcode(), brandId)) {
            throw new IllegalArgumentException("브랜드 내에 동일한 바코드가 있습니다.");
        }

        return itemRepository.save(Item.createItem(itemCreateDto, category)).getId();
    }

    @Override
    public ItemDetailDto getItem(Long itemId) {

        return itemRepository.findByIdCustom(itemId).orElseThrow(
                () -> new IllegalArgumentException("해당 하는 이이템이 없습니다.")
        );
    }

    @Override
    @Transactional
    public void changeItemIsSerial(Long itemId) {
        // TODO N+1 문제 해결
        Item item = itemRepository.findById(itemId).orElseThrow(
                () -> new IllegalArgumentException("해당 하는 이이템이 없습니다.")
        );

        if (item.getIsSerial() && itemRepository.custom(itemId)) {
            throw new IllegalArgumentException("이 아이템에 등록 된 시리얼 번호를 지우고 다시 시도해주세요");
        }

        item.changeIsSerial();
    }
}
