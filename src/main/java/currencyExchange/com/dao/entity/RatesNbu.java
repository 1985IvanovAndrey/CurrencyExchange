package currencyExchange.com.dao.entity;

import lombok.Data;

@Data
public class RatesNbu implements Comparable<RatesNbu>{
    private int id;
    private String ccy;
    private String base_ccy;
    private Double rate;


    public RatesNbu(int id, String ccy, String base_ccy, Double rate) {
        this.id = id;
        this.ccy = ccy;
        this.base_ccy = base_ccy;
        this.rate = rate;
    }

    public RatesNbu(String ccy, String base_ccy, Double rate) {
        this.ccy = ccy;
        this.base_ccy = base_ccy;
        this.rate = rate;
    }

    public RatesNbu() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        RatesNbu ratesNbu = (RatesNbu) o;

        if (id != ratesNbu.id) return false;
        if (ccy != null ? !ccy.equals(ratesNbu.ccy) : ratesNbu.ccy != null) return false;
        if (base_ccy != null ? !base_ccy.equals(ratesNbu.base_ccy) : ratesNbu.base_ccy != null) return false;
        return rate != null ? rate.equals(ratesNbu.rate) : ratesNbu.rate == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id;
        result = 31 * result + (ccy != null ? ccy.hashCode() : 0);
        result = 31 * result + (base_ccy != null ? base_ccy.hashCode() : 0);
        result = 31 * result + (rate != null ? rate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RatesNbu{" +
                "id=" + id +
                ", ccy='" + ccy + '\'' +
                ", base_ccy='" + base_ccy + '\'' +
                ", rate=" + rate +
                '}';
    }

    @Override
    public int compareTo(RatesNbu ratesNbu) {
        return ccy.compareTo(ratesNbu.ccy);
    }
}
