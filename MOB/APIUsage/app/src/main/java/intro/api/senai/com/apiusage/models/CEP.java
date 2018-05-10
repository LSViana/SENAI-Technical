package intro.api.senai.com.apiusage.models;

public class CEP {

    private String bairro;
    private String cep;
    private String cidade;
    private CidadeInfo cidade_info;
    private String complemento;
    private String estado;
    private EstadoInfo estado_info;
    private String logradouro;

    public CEP() {
        // Standard Constructor for JSON Parse
    }

    public CEP(String bairro, String cep, String cidade, CidadeInfo cidade_info, String complemento, String estado, EstadoInfo estado_info, String logradouro) {
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.cidade_info = cidade_info;
        this.complemento = complemento;
        this.estado = estado;
        this.estado_info = estado_info;
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public CidadeInfo getCidade_info() {
        return cidade_info;
    }

    public void setCidade_info(CidadeInfo cidade_info) {
        this.cidade_info = cidade_info;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public EstadoInfo getEstado_info() {
        return estado_info;
    }

    public void setEstado_info(EstadoInfo estado_info) {
        this.estado_info = estado_info;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    @Override
    public String toString() {
        return "CEP{" +
                "bairro='" + bairro + '\'' +
                ", cep='" + cep + '\'' +
                ", cidade='" + cidade + '\'' +
                ", cidade_info=" + cidade_info +
                ", complemento='" + complemento + '\'' +
                ", estado='" + estado + '\'' +
                ", estado_info=" + estado_info +
                ", logradouro='" + logradouro + '\'' +
                '}';
    }

}