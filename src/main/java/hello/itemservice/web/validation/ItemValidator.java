package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ItemValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Item.class.isAssignableFrom(clazz);
        // item == clazz ? 맞으면 통과
        // item == subItem ? 맞으면 통과
    }

    @Override
    public void validate(Object target, Errors errors) {
        Item item = (Item) target;

        if (!StringUtils.hasText(item.getItemName())) {
            errors.rejectValue("itemName","required");
            //rejectValue field 오류 필드명 / errorCode 에러코드명 / errArgs 오류메시지에서 {0}을 치환하기 위한 값 / defaultMessasge 오류메시지를 찾을수없을때 쓰이는 기본메시지
        }

        if (!StringUtils.hasText(item.getItemName())) {
            errors.rejectValue("itemName","required");
//            ValidationUtils.rejectIfEmpty(bindingResult, "itemName", "required"); 위에거랑 똑같다.
            //rejectValue field 오류 필드명 / errorCode 에러코드명 / errArgs 오류메시지에서 {0}을 치환하기 위한 값 / defaultMessasge 오류메시지를 찾을수없을때 쓰이는 기본메시지
        }
        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            errors.rejectValue("price","range", new Object[]{1000,1000000}, null);
        }
        if (item.getQuantity() == null || item.getQuantity() == 0) {
            errors.rejectValue("quantity","min",new Object[]{1},null);
        }else if(item.getQuantity() >=9999) {
            errors.rejectValue("quantity","max",new Object[]{10000},null);
        }
        //특정 필드 예외가 아닌 전체 예외
        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < 10000) {
                errors.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }
    }
}
