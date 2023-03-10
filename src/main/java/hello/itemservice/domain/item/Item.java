package hello.itemservice.domain.item;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
//@ScriptAssert(lang = "javascript", script = "_this.price * _this.quantity >= 10000", message = "총합이 10000원을 넘어야합니다.") //Object 오류,제약조건이 너무 많아서 별로..
public class Item {

//    @NotNull(groups = UpdateCheck.class) //수정요구사항 추가, 그룹으로 충돌을 방지한다
    private Long id;
//    @NotBlank(groups = {SaveCheck.class, UpdateCheck.class}) //공백 허용안함
    // 가입할때랑 수정할때 데이터가 많이 다르다보니 실제로는 Group 잘 안쓴다.
    private String itemName;
//    @NotNull(groups = {SaveCheck.class, UpdateCheck.class}) //NUll 허용안함
//    @Range(min = 1000, max = 1000000) //범위값 안의 단위여야한다. 문자입력시 Typemismatch
    private Integer price;

//    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
//    @Min(value = 1, groups = {SaveCheck.class}) //최소허용 범위
//    @Max(value = 9999, groups = {SaveCheck.class}) //최대값허용 범위, 수정 요구사항 추가
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
