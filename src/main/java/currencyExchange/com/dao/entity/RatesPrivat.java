package currencyExchange.com.dao.entity;

import lombok.Data;

@Data
public class RatesPrivat implements Comparable<RatesPrivat>{

    private int id;
    private String ccy;
    private String base_ccy;
    private Double sale;
    private Double buy;


    public RatesPrivat() {
    }

    public RatesPrivat(String ccy, String base_ccy, Double sale, Double buy) {
        this.ccy = ccy;
        this.base_ccy = base_ccy;
        this.sale = sale;
        this.buy = buy;
    }

    public RatesPrivat(int id, String ccy, String base_ccy, Double sale, Double buy) {
        this.id = id;
        this.ccy = ccy;
        this.base_ccy = base_ccy;
        this.sale = sale;
        this.buy = buy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        RatesPrivat that = (RatesPrivat) o;

        if (id != that.id) return false;
        if (ccy != null ? !ccy.equals(that.ccy) : that.ccy != null) return false;
        if (base_ccy != null ? !base_ccy.equals(that.base_ccy) : that.base_ccy != null) return false;
        if (sale != null ? !sale.equals(that.sale) : that.sale != null) return false;
        return buy != null ? buy.equals(that.buy) : that.buy == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id;
        result = 31 * result + (ccy != null ? ccy.hashCode() : 0);
        result = 31 * result + (base_ccy != null ? base_ccy.hashCode() : 0);
        result = 31 * result + (sale != null ? sale.hashCode() : 0);
        result = 31 * result + (buy != null ? buy.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RatesPrivat{" +
                "id=" + id +
                ", ccy='" + ccy + '\'' +
                ", base_ccy='" + base_ccy + '\'' +
                ", sale=" + sale +
                ", buy=" + buy +
                '}';
    }

    @Override
    public int compareTo(RatesPrivat ratesPrivat) {
        return ccy.compareTo(ratesPrivat.ccy);
    }
}
