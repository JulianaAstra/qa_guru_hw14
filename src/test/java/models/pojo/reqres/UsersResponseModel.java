package models.pojo.reqres;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UsersResponseModel {

    Integer page;
    @JsonProperty("per_page")
    Integer perPage;
    Integer total;
    @JsonProperty("total_pages")
    Integer totalPages;

    DataModel[] data;

    public Integer getPage() {return page;}

    public Integer getPerPage() {return perPage;}

    public Integer getTotal() {return total;}

    public Integer getTotalPages() {return totalPages;}

    public DataModel[] getData() {return data;}
}
