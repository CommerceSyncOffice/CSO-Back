package commercesyncoffice.org.domain.storeitem.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import commercesyncoffice.org.domain.admin.Admin;
import commercesyncoffice.org.domain.admin.repository.AdminRepository;
import commercesyncoffice.org.domain.brand.Brand;
import commercesyncoffice.org.domain.brand.dto.BrandCreateDto;
import commercesyncoffice.org.domain.brand.repository.BrandRepository;
import commercesyncoffice.org.domain.item.Item;
import commercesyncoffice.org.domain.item.dto.ItemCreateDto;
import commercesyncoffice.org.domain.item.repository.ItemRepository;
import commercesyncoffice.org.domain.store.Store;
import commercesyncoffice.org.domain.store.dto.StoreCreateDto;
import commercesyncoffice.org.domain.store.repository.StoreRepository;
import commercesyncoffice.org.domain.storeitem.StoreItem;
import commercesyncoffice.org.domain.storeitem.dto.StoreItemCreateDto;
import commercesyncoffice.org.domain.storeitem.dto.StoreItemSaleDto;
import commercesyncoffice.org.domain.storeitem.repository.StoreItemRepository;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class StoreItemServiceImplV1Test {

    @Autowired
    private StoreItemService storeItemService;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private StoreItemRepository storeItemRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ItemRepository itemRepository;

    private StoreItem storeItem;

    @BeforeEach
    void setUp() {

        Admin admin = Admin.builder().id(1L).build();
        adminRepository.save(admin);
        Brand brand = Brand.createBrand(new BrandCreateDto("브랜드 이름", "브랜드 설명"), admin);
        brandRepository.save(brand);
        Store store = Store.createStore(brand, new StoreCreateDto("스토어", "주소"));
        storeRepository.save(store);
        Item item = Item.createItem(
                new ItemCreateDto("아이템 이름", 5000, 10000, "설명", "이미지 링크", "A1", false, null), null,
                brand);
        itemRepository.save(item);
        storeItem = StoreItem.createStoreItem(store, item,
                new StoreItemCreateDto(item.getId(), 100, 0, 0));
        storeItem = storeItemRepository.save(storeItem);
    }

    @AfterEach
    void tearDown() {
        storeItemRepository.deleteAll();
    }

    @Test
    @DisplayName("수량 1개 감소 테스트")
    void test() {
        // Given
        Integer saleStock = 1;
        StoreItemSaleDto storeItemSaleDto = new StoreItemSaleDto(saleStock);
        // When
        storeItemService.saleStoreItem(storeItem.getId(), storeItemSaleDto);

        // Then
        StoreItem findStoreItem = storeItemRepository.findById(storeItem.getId()).orElseThrow();

        assertThat(findStoreItem.getStock()).isEqualTo(99);
        assertThat(findStoreItem.getSaleCnt()).isEqualTo(1);
    }

    @Test
    @DisplayName("동시에 100개의 요청으로 수량 감소 테스트")
    void decrease_100_request_to_store_item() throws InterruptedException {
        // Given
        Integer saleStock = 1;
        StoreItemSaleDto storeItemSaleDto = new StoreItemSaleDto(saleStock);

        final int threadCnt = 100;
        final ExecutorService executorService = Executors.newFixedThreadPool(32);
        final CountDownLatch countDownLatch = new CountDownLatch(threadCnt);

        // When
        for (int i = 0; i < threadCnt; i++) {
            executorService.submit(() -> {
                try {
                    storeItemService.saleStoreItem(storeItem.getId(), storeItemSaleDto);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();
        // Then
        StoreItem findStoreItem = storeItemRepository.findById(storeItem.getId()).orElseThrow();
        assertThat(findStoreItem.getStock()).isEqualTo(0);
        assertThat(findStoreItem.getSaleCnt()).isEqualTo(100);
    }
}