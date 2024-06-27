package br.com.jeff.securityTraining.Dto;

import lombok.Data;

@Data
public class ResponseAuthDto {
    private String acessToken;
    private String tokenType = "Bearer ";

    public ResponseAuthDto  (String acessToken){
        this.acessToken = acessToken;
    }
}
