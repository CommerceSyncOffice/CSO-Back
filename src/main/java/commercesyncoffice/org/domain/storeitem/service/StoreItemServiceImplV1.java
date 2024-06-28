package commercesyncoffice.org.domain.storeitem.service;

import commercesyncoffice.org.domain.brand.service.BrandService;
import commercesyncoffice.org.domain.item.Item;
import commercesyncoffice.org.domain.item.service.ItemService;
import commercesyncoffice.org.domain.store.Store;
import commercesyncoffice.org.domain.store.service.StoreService;
import commercesyncoffice.org.domain.storeitem.StoreItem;
import commercesyncoffice.org.domain.storeitem.dto.StoreItemCreateDto;
import commercesyncoffice.org.domain.storeitem.dto.StoreItemSaleDto;
import commercesyncoffice.org.domain.storeitem.repository.StoreItemRepository;
import commercesyncoffice.org.global.exception.CustomException;
import commercesyncoffice.org.global.exception.ExceptionCode;
import commercesyncoffice.org.global.jwt.JwtUtil;
import commercesyncoffice.org.global.security.UserDetailsImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreItemServiceImplV1 implements StoreItemService {

    private final StoreItemRepository storeItemRepository;
    private final StoreService storeService;
    private final ItemService itemService;
    private final BrandService brandService;

    @Override
    @Transactional
    public void createStoreItem(UserDetails userDetails, Long storeId,
            StoreItemCreateDto storeItemCreateDto) {

        Store store = storeService.getStoreById(storeId);
        Item item = itemService.getItemWithBrandByItemId(storeItemCreateDto.itemId());
        brandService.validateBrand(userDetails, item.getBrandId());

        if (storeItemRepository.existsByItemIdAndStoreId(item.getId(), store.getId())) {
            throw new CustomException(ExceptionCode.ALREADY_REGISTERED_ITEM);
        }

        storeItemRepository.save(StoreItem.createStoreItem(store, item, storeItemCreateDto));
    }

    @Override
    @Transactional
    public void saleStoreItem(UserDetails userDetails, Long storeItemId,
            StoreItemSaleDto storeItemSaleDto) {

        StoreItem storeItem = storeItemRepository.findByIdWithPessimisticLock(storeItemId)
                .orElseThrow(
                        () -> new CustomException(ExceptionCode.NOT_FOUND_STORE_ITEM)
                );

        validateUserDetails(userDetails, storeItemId);

        storeItem.saleStock(storeItemSaleDto);
    }

    private void validateUserDetails(UserDetails userDetails, Long storeItemId) {

        if (((UserDetailsImpl) userDetails).getRole().equals(JwtUtil.MEMBER)) {

            if (!storeItemRepository.existsMemberByStoreItemIdAndMemberUsername(storeItemId, userDetails.getUsername())) {
                throw new CustomException(ExceptionCode.YOUR_NOT_MEMBER_THIS_BRAND);
            }
        } else if (((UserDetailsImpl) userDetails).getRole().equals(JwtUtil.ADMIN)) {

            if (!storeItemRepository.existsMemberByStoreItemIdAndAdminUsername(storeItemId, userDetails.getUsername())) {
                throw new CustomException(ExceptionCode.YOUR_NOT_ADMIN_THIS_BRAND);
            }
        }
    }
}
