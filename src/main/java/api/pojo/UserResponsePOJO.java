package api.pojo;

import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponsePOJO {
    private String name;
    private String job;
    private String id;
    private String createdAt;
}