package api.pojo;

import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TodosResponsePOJO {

    private List<Object> data;
    private int total;
    private int page;
    private int per_page;
}