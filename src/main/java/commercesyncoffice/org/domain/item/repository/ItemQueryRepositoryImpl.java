//package commercesyncoffice.org.domain.item.repository;
//
//import static commercesyncoffice.org.domain.category.QCategory.category;
//import static commercesyncoffice.org.domain.item.QItem.item;
//import static commercesyncoffice.org.domain.itemserial.QItemSerial.itemSerial;
//
//import com.querydsl.core.types.Expression;
//import com.querydsl.core.types.ExpressionUtils;
//import com.querydsl.core.types.Projections;
//import com.querydsl.core.types.dsl.BooleanExpression;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import commercesyncoffice.org.domain.item.dto.ItemDetailDto;
//import commercesyncoffice.org.domain.itemserial.ItemSerial;
//import commercesyncoffice.org.domain.itemserial.QItemSerial;
//import commercesyncoffice.org.domain.itemserial.dto.ItemSerialSimpleDto;
//import jakarta.persistence.EntityManager;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class ItemQueryRepositoryImpl implements ItemQueryRepository {
//
//    private final EntityManager em;
//    private final JPAQueryFactory queryFactory;
//
//    public ItemQueryRepositoryImpl(EntityManager em) {
//        this.em = em;
//        this.queryFactory = new JPAQueryFactory(em);
//    }
//
//    @Override
//    public Optional<ItemDetailDto> findByIdCustom(Long itemId) {
//
//        return Optional.ofNullable(queryFactory
//                .select(Projections.constructor(ItemDetailDto.class,
//                        item.name,
//                        item.description,
//                        category.name,
//                        item.barcode,
//                        item.originPrice,
//                        item.price,
//                        item.isSerial,
//                        item.img,
//                        Projections.list(Projections.constructor(ItemSerialSimpleDto.class,
//                                itemSerial.serial))
//                ))
//                .from(item)
//                .leftJoin(category).on(category.id.eq(item.category.id))
//                .leftJoin(itemSerial).on(itemSerial.item.id.eq(itemId))
//                .where(item.id.eq(itemId))
//                .fetchOne());
//    }
//}
