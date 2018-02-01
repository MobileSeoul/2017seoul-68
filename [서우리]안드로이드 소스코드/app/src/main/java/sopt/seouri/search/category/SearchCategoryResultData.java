package sopt.seouri.search.category;

/**
 * Created by 김지원 on 2017-10-05.
 */

public class SearchCategoryResultData {
    public String villageEnterpriseId;  // id
    public String name;     // 마을기업 이름
    public String photo;    // 사진
    public String category; // 카테고리

    public SearchCategoryResultData(String villageEnterpriseId, String name, String photo, String category) {
        this.villageEnterpriseId = villageEnterpriseId;
        this.name = name;
        this.photo = photo;
        this.category = category;
    }
}
