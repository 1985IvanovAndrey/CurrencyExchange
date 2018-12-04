package currencyExchange.com.dao.entity;

import lombok.Data;

@Data
public class RatesPrivat implements Comparable<RatesPrivat>{

    private int id;
    private String nameCurrency;
    private Double sale;
    private Double buy;


    public RatesPrivat() {
    }

    public RatesPrivat(int id, String nameCurrency, Double sale, Double buy) {
        this.id = id;
        this.nameCurrency = nameCurrency;
        this.sale = sale;
        this.buy = buy;
    }

    @Override
    public String toString() {
        return "RatesPrivat{" +
                "id=" + id +
                ", nameCurrency='" + nameCurrency + '\'' +
                ", sale='" + sale + '\'' +
                ", buy='" + buy + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        RatesPrivat RatesPrivat = (RatesPrivat) o;

        if (id != RatesPrivat.id) return false;
        if (nameCurrency != null ? !nameCurrency.equals(RatesPrivat.nameCurrency) : RatesPrivat.nameCurrency != null)
            return false;
        if (sale != null ? !sale.equals(RatesPrivat.sale) : RatesPrivat.sale != null) return false;
        return buy != null ? buy.equals(RatesPrivat.buy) : RatesPrivat.buy == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id;
        result = 31 * result + (nameCurrency != null ? nameCurrency.hashCode() : 0);
        result = 31 * result + (sale != null ? sale.hashCode() : 0);
        result = 31 * result + (buy != null ? buy.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(RatesPrivat ratesPrivat) {
        return nameCurrency.compareTo(ratesPrivat.nameCurrency);
    }
}
