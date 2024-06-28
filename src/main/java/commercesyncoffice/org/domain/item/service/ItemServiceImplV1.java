package commercesyncoffice.org.domain.item.service;

import commercesyncoffice.org.domain.brand.Brand;
import commercesyncoffice.org.domain.brand.service.BrandService;
import commercesyncoffice.org.domain.category.Category;
import commercesyncoffice.org.domain.category.service.CategoryService;
import commercesyncoffice.org.domain.item.Item;
import commercesyncoffice.org.domain.item.dto.ItemChangeCategoryDto;
import commercesyncoffice.org.domain.item.dto.ItemCreateDto;
import commercesyncoffice.org.domain.item.dto.ItemDetailBeforeMixDto;
import commercesyncoffice.org.domain.item.dto.ItemDetailDto;
import commercesyncoffice.org.domain.item.repository.ItemRepository;
import commercesyncoffice.org.domain.itemserial.dto.ItemSerialSimpleDto;
import commercesyncoffice.org.global.exception.CustomException;
import commercesyncoffice.org.global.exception.ExceptionCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Primary
public class ItemServiceImplV1 implements ItemService {

    private final ItemRepository itemRepository;
    private final BrandService brandService;
    private final CategoryService categoryService;

    @Override
    @Transactional
    public Long createItem(UserDetails userDetails, ItemCreateDto itemCreateDto, Long brandId) {

        Category category = null;

        Brand brand = brandService.getBrandById(brandId);

        brandService.validateBrand(userDetails, brandId);

        if (itemCreateDto.categoryId() != null) {
            category = categoryService.getCategoryByIdAndBrandId(itemCreateDto.categoryId(),
                    brandId);
        }

        if (itemRepository.checkSameBarcodeInBrand(itemCreateDto.barcode(), brandId)) {
            throw new CustomException(ExceptionCode.SAME_BARCODE_IN_BRAND);
        }

        return itemRepository.save(Item.createItem(itemCreateDto, category, brand)).getId();
    }

    @Override
    public ItemDetailDto getItem(UserDetails userDetails, Long itemId) {

        Item item = getItemWithBrandByItemId(itemId);
        brandService.validateBrand(userDetails, item.getBrandId());

        ItemDetailBeforeMixDto itemDetailBeforeMixDto = itemRepository.findByIdCustom(itemId)
                .orElseThrow(
                        () -> new CustomException(ExceptionCode.NOT_FOUND_ITEM)
                );

        List<ItemSerialSimpleDto> serialCustom = itemRepository.findSerialCustom(itemId).orElseGet(null);

        return new ItemDetailDto(
                itemDetailBeforeMixDto.name(),
                itemDetailBeforeMixDto.description(),
                itemDetailBeforeMixDto.category(),
                itemDetailBeforeMixDto.barcode(),
                itemDetailBeforeMixDto.originPrice(),
                itemDetailBeforeMixDto.price(),
                itemDetailBeforeMixDto.isSerial(),
                itemDetailBeforeMixDto.img(),
                serialCustom);
    }

    @Override
    @Transactional
    public void changeItemIsSerial(UserDetails userDetails, Long itemId) {

        Item item = getItemWithBrandByItemId(itemId);
        brandService.validateBrand(userDetails, item.getBrandId());

        if (item.getIsSerial() && itemRepository.isHavingSerial(itemId)) {
            throw new CustomException(ExceptionCode.DELETE_SERIAL_THIS_ITEM);
        }

        item.changeIsSerial();
    }

    @Override
    @Transactional
    public void changeItemCategory(UserDetails userDetails, Long itemId, ItemChangeCategoryDto itemChangeCategoryDto) {

        Category category = null;

        Item item = getItemWithBrandByItemId(itemId);
        brandService.validateBrand(userDetails, item.getBrandId());

        if (itemChangeCategoryDto.categoryId() != null) {
            category = categoryService.getCategoryByIdAndBrandId(itemChangeCategoryDto.categoryId(),
                    item.getBrandId());
        }

        item.changeCategory(category);
    }

    @Override
    public Item getItemWithBrandByItemId(Long brandId) {

        return itemRepository.findItemWithBrandByItemId(brandId).orElseThrow(
                () -> new CustomException(ExceptionCode.NOT_FOUND_ITEM)
        );
    }

    @Override
    public Item getItemById(Long itemId) {

        return itemRepository.findById(itemId).orElseThrow(
                () -> new CustomException(ExceptionCode.NOT_FOUND_ITEM)
        );
    }
}
