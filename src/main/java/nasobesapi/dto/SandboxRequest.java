package nasobesapi.dto;

import lombok.Data;

@Data
public class SandboxRequest {

    private Integer number = null;
    private String devSpecification;
    private String grade;

}
