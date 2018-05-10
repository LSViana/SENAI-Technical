package intro.api.senai.com.apiusage.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CNPJ {

    private String nome;
    private String numero;
    private String uf;
    private String telefone;
    private String cep;
    private String email;
    private String abertura;

    public CNPJ() {
        // Standard constructor
    }

    public CNPJ(String nome, String numero, String uf, String telefone, String cep, String email, String abertura) {
        this.nome = nome;
        this.numero = numero;
        this.uf = uf;
        this.telefone = telefone;
        this.cep = cep;
        this.email = email;
        this.abertura = abertura;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAbertura() {
        return abertura;
    }

    public void setAbertura(String abertura) {
        this.abertura = abertura;
    }

    @Override
    public String toString() {
        return "CNPJ{" +
                "nome='" + nome + '\'' +
                ", numero='" + numero + '\'' +
                ", uf='" + uf + '\'' +
                ", telefone='" + telefone + '\'' +
                ", cep='" + cep + '\'' +
                ", email='" + email + '\'' +
                ", abertura='" + abertura + '\'' +
                '}';
    }

}
