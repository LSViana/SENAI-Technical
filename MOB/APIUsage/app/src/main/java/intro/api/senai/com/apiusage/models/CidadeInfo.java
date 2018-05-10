package intro.api.senai.com.apiusage.models;

public class CidadeInfo {

    private String area_km2;
    private String codigo_ibge;

    public CidadeInfo() {
        // Standard Constructor for JSON Parse
    }

    public CidadeInfo(String area_km2, String codigo_ibge) {
        this.area_km2 = area_km2;
        this.codigo_ibge = codigo_ibge;
    }

    public String getArea_km2() {
        return area_km2;
    }

    public void setArea_km2(String area_km2) {
        this.area_km2 = area_km2;
    }

    public String getCodigo_ibge() {
        return codigo_ibge;
    }

    public void setCodigo_ibge(String codigo_ibge) {
        this.codigo_ibge = codigo_ibge;
    }

    @Override
    public String toString() {
        return "CidadeInfo{" +
                "area_km2='" + area_km2 + '\'' +
                ", codigo_ibge='" + codigo_ibge + '\'' +
                '}';
    }

}
